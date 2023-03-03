package com.fjy.demo.register.server;

/**
 * 代表了一个服务实例，里面包含了服务实例的所有信息
 * 比如服务名称，ip地址，hostname，端口号，服务实例id
 * 契约信息
 */
public class ServiceInstance {
    private static final Long NOT_ALIVE_PERIOD = 90*1000L;
    private String servicename;
    private String ip;
    private String hostname;
    private int port;
    private String serviceInstanceId;

    private Lease lease;

    public ServiceInstance(){
        this.lease = new Lease();
    }

    public void renew(){
        this.lease.renew();
    }

    /**
     * 契约
     * 维护了一个服务实例和当前的注册中心的关系，包括了心跳时间
     */
    private class Lease {

        private Long latestHeartbeatTime=System.currentTimeMillis();

        /**
         * 续约：相当于register-client和register-server进行续约
         * @param
         */
        public void renew() {
            this.latestHeartbeatTime = System.currentTimeMillis();
            System.out.println("服务实例【"+serviceInstanceId+"】，进行续约："+latestHeartbeatTime);
        }

        /**
         * 判断当前的服务实例的契约是否还存在
         * @return
         */
        public Boolean isAlive(){
            Long currentTime = System.currentTimeMillis();
            if(currentTime-latestHeartbeatTime>NOT_ALIVE_PERIOD ){
                System.out.println("服务实例【"+serviceInstanceId+"】,不在存活");
                return false;
            }else{
                System.out.println("服务实例【"+serviceInstanceId+"】,保持存活");
                return true;
            }
        }
        @Override
        public String toString() {
            return "Lease{" +
                    "latestHeartbeatTime=" + latestHeartbeatTime +
                    '}';
        }
    }
    public Boolean isAlive(){
        return lease.isAlive();
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public Lease getLease() {
        return lease;
    }

    public void setLease(Lease lease) {
        this.lease = lease;
    }

    @Override
    public String toString() {
        return "ServiceInstance{" +
                "servicename='" + servicename + '\'' +
                ", ip='" + ip + '\'' +
                ", hostname='" + hostname + '\'' +
                ", port=" + port +
                ", serviceInstanceId='" + serviceInstanceId + '\'' +
                ", lease=" + lease +
                '}';
    }
}
