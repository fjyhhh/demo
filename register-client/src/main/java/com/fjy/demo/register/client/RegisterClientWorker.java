package com.fjy.demo.register.client;

import com.fjy.demo.register.common.HeartbeatRequest;
import com.fjy.demo.register.common.HeartbeatResponse;
import com.fjy.demo.register.common.RegisterRequest;
import com.fjy.demo.register.common.RegisterResponse;

/**
 * 用来发送注册申请
 */
public class RegisterClientWorker extends Thread {
    /**
     * http通信组件
     */
    private HttpSender httpSender;

    /**
     * 是否完成注册
     */
    private Boolean finishedRegister;

    private String serviceInstanceId;
    public RegisterClientWorker(String serviceInstanceId){
        this.httpSender= new HttpSender();
        this.finishedRegister = false;
        this.serviceInstanceId = serviceInstanceId;
    }
    /**
     * 这些信息应该是从配置文件中取得的
     */
    public static final String SERVICE_NAME = "inventory-service";
    public static final String IP="192.168.31.207";
    public static final String HOSTNAME = "inventory01";
    public static final int PORT = 9000;
    @Override
    public void run() {
        if(!finishedRegister){
            RegisterRequest registerrequest = new RegisterRequest();
            registerrequest.setServiceName(SERVICE_NAME);
            registerrequest.setIp(IP);
            registerrequest.setHostName(HOSTNAME);
            registerrequest.setPort(PORT);
            registerrequest.setServiceInstanceId(serviceInstanceId);

            RegisterResponse registerresponse = httpSender.register(registerrequest);
            System.out.println("服务注册的结果是"+registerresponse.getStatus()+"...");
            if(RegisterResponse.SUCCESS.equals(registerresponse.getStatus())){
                this.finishedRegister=true;
            }else{
                return;
            }
        }
        //如果注册成功了，就进入while循环
        if(finishedRegister){
            HeartbeatRequest heartbeatRequest = new HeartbeatRequest();
            heartbeatRequest.setServiceInstanceId(serviceInstanceId);
            HeartbeatResponse heartbeatResponse = null;
            while(true){
                heartbeatResponse = httpSender.heartbeat(heartbeatRequest);
                System.out.println("心跳的结果为： "+heartbeatResponse.getStatus()+"....");
                try {
                    Thread.sleep(30000);//让线程每隔30s一次心跳
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
