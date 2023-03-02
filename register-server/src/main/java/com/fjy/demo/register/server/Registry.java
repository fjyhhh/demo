package com.fjy.demo.register.server;

import java.util.HashMap;
import java.util.Map;

/**
 * 注册表:单例模式
 */
public class Registry {
    private static Registry instance = new Registry();
    private Registry(){

    }

    /**
     * 第一个key是服务名称，value是所有的服务实例
     * 第二个key是服务实例的id，value就是实例
     */
    private Map<String, Map<String,ServiceInstance>> registry =
            new HashMap<String, Map<String,ServiceInstance>>();

    /**
     * 服务注册
     * @param serviceInstance
     */
    public void register(ServiceInstance serviceInstance){
        Map<String,ServiceInstance> serviceInstanceMap = registry.get(serviceInstance.getServicename());
        if(serviceInstanceMap==null){
            serviceInstanceMap = new HashMap<String,ServiceInstance>();
            registry.put(serviceInstance.getServicename(),serviceInstanceMap);
        }
        serviceInstanceMap.put(serviceInstance.getServiceInstanceId(),serviceInstance);
        System.out.println("服务实例【"+serviceInstance+"】完成注册...");
        System.out.println("注册表"+registry);
    }

    /**
     * 获取服务实例
     * @param serviceName
     * @param serviceInstanceId
     * @return
     */
    public ServiceInstance getServiceInstance(String serviceName,String serviceInstanceId){
        Map<String,ServiceInstance> serviceInstanceMap = registry.get(serviceName);
        return serviceInstanceMap.get(serviceInstanceId);
    }
    public static Registry getInstance(){
        return instance;
    }
}
