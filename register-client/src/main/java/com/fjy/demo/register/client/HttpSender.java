package com.fjy.demo.register.client;

import com.fjy.demo.register.common.HeartbeatRequest;
import com.fjy.demo.register.common.HeartbeatResponse;
import com.fjy.demo.register.common.RegisterRequest;
import com.fjy.demo.register.common.RegisterResponse;

/**
 * 负责发送各种hhtp的方法
 */
public class HttpSender {
    /**
     * 发送注册的请求
     * @return
     */
    public RegisterResponse register(RegisterRequest request){
        //实际上基于类似于HttpClient这种开元的网络包
        //可以构造一个请求，里面放入这个服务的实例信息，比如服务名称，ip地址，端口号
        //通过这个请求发送过去
        System.out.println("服务实例【"+request+"】发送发送请求进行注册");

        //正常来说收到register-server的响应之后，封装一个response对象
        RegisterResponse response =new RegisterResponse();
        response.setStatus(RegisterResponse.SUCCESS);
        return response;
    }

    /**
     * 发送心跳的请求
     * @return
     */

    public HeartbeatResponse heartbeat(HeartbeatRequest request){
        //实际上基于类似于HttpClient这种开元的网络包
        //可以构造一个请求，里面放入这个服务的实例信息，比如服务名称，ip地址，端口号
        //通过这个请求发送过去
        System.out.println("服务实例【"+request.getServiceInstanceId()+"】发送心跳请求");

        //正常来说收到register-server的响应之后，封装一个response对象
        HeartbeatResponse response =new HeartbeatResponse();
        response.setStatus(HeartbeatResponse.SUCCESS);
        return response;
    }

}
