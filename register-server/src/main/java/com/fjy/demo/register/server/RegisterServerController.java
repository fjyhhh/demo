package com.fjy.demo.register.server;

import com.fjy.demo.register.common.HeartbeatRequest;
import com.fjy.demo.register.common.HeartbeatResponse;
import com.fjy.demo.register.common.RegisterRequest;
import com.fjy.demo.register.common.RegisterResponse;

/**
 * controller用于接收register-client的请求
 */
public class RegisterServerController {
    private Registry registry  = Registry.getInstance();
    /**
     * 服务注册，
     */

    public RegisterResponse register(RegisterRequest registerRequest){
        //这个响应其实是根据request得到的response
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            ServiceInstance serviceInstance = new ServiceInstance();
            serviceInstance.setHostname(registerRequest.getHostName());
            serviceInstance.setIp(registerRequest.getIp());
            serviceInstance.setServiceInstanceId(registerRequest.getServiceInstanceId());
            serviceInstance.setServicename(registerRequest.getServiceName());
            registry.register(serviceInstance);
            registerResponse.setStatus(RegisterResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            registerResponse.setStatus(RegisterResponse.Failure);
        }
    return registerResponse;
    }

    /**
     *
     * @param heartbeatRequest
     * @return
     */
    public HeartbeatResponse heartbeat(HeartbeatRequest heartbeatRequest){
        HeartbeatResponse heartbeatResponse = new HeartbeatResponse();
        try{
            ServiceInstance serviceInstance = registry.getServiceInstance(heartbeatRequest.getServiceName()
                    ,heartbeatRequest.getServiceInstanceId());
            serviceInstance.renew();
            heartbeatResponse.setStatus(HeartbeatResponse.SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            heartbeatResponse.setStatus(HeartbeatResponse.Failure);
        }
       return heartbeatResponse;
    }
}
