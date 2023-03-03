package com.fjy.demo.register.server;

import java.util.Map;

/**
 * 微服务存活状态监控组件
 */
public class ServiceAliveMonitor {
    /**
     * 每隔60s检查一下服务是否存活
     */
    private static final Long CHECK_ALIVE_INTERVAL = 60*1000L;

    private Daemon daemon;

    /**
     * 将serviceMonitor线程设置为daemon线程，如果工作线程退出，那么daemon线程也会退出
     * 工作线程（main）线程退出，daemon线程不会阻止jvm进程退出
     */
    public ServiceAliveMonitor(){
        ThreadGroup daemonThreadGroup = new ThreadGroup("daemon");
        this.daemon = new Daemon(daemonThreadGroup,"ServiceAliveMonitor");
        daemon.setDaemon(true);
    }

    /**
     *
     */
    public void start(){
        daemon.start();
    }
    /**
     * 负责监控微服务存活状态的后台线程
     */
    private class Daemon extends Thread{
        private Registry registry = Registry.getInstance();//Registry是一个单例的设计模式，通过该方法得到其实例

        public Daemon(ThreadGroup threadGroup,String name){
            super(threadGroup,name);
        }
        @Override
        public void run() {
            Map<String, Map<String,ServiceInstance>> registryMap =null;
            while (true){
                //每隔60s进行一次检查，如果超过90s还没发送心跳，那么就从注册表里面摘除该实例
                try {
                    registryMap =
                            registry.getRegistry();
                    //遍历所有的服务名称
                    for(String serviceName:registryMap.keySet()){
                        Map<String,ServiceInstance> serviceInstanceMap =
                                registryMap.get(serviceName);
                        for(ServiceInstance serviceInstance:serviceInstanceMap.values()){
                            /**
                             * 说明服务实例距离上一次心跳已经超过90s，认为这个服务已经死了从注册表中摘除这个服务实例
                             */
                            if(!serviceInstance.isAlive()){
                                registry.remove(serviceName,serviceInstance.getServiceInstanceId());
                            }
                        }
                    }
                    Thread.sleep(CHECK_ALIVE_INTERVAL);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
