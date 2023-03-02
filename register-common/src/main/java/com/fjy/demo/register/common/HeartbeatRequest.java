package com.fjy.demo.register.common;

/**
 * 心跳请求
 */
public class HeartbeatRequest {
    //服务实例id
    private String serviceInstanceId;

    //服务实例名称
    public String serviceName;

    public String getServiceInstanceId() {
        return serviceInstanceId;
    }

    public void setServiceInstanceId(String serviceInstanceId) {
        this.serviceInstanceId = serviceInstanceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
