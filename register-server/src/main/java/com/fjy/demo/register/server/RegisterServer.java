package com.fjy.demo.register.server;

import com.fjy.demo.register.common.HeartbeatRequest;
import com.fjy.demo.register.common.RegisterRequest;

import java.util.UUID;

/**
 * 代表和服务注册中心，Euraka是一个web服务器
 */
public class RegisterServer {
    public static void main(String[] args) throws Exception {
        RegisterServerController controller = new RegisterServerController();
        String serviceInstanceId = UUID.randomUUID().toString().replace("-","");
        //模拟发送服务注册请求
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setHostName("inventory-service-01");
        registerRequest.setIp("192.168.31.208");
        registerRequest.setPort(9000);
        registerRequest.setServiceInstanceId(serviceInstanceId);
        registerRequest.setServiceName("inventory-service");
        controller.register(registerRequest);
        // 模拟进行一次心跳，完成续约
        HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
        heartbeatRequest.setServiceName("inventory-service");
        heartbeatRequest.setServiceInstanceId(serviceInstanceId);
        controller.heartbeat(heartbeatRequest);
        while(true){
            Thread.sleep(30);
        }
    }
}
