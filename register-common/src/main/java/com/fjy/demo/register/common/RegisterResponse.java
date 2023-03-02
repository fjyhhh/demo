package com.fjy.demo.register.common;

/**
 * 用来封装register-server是否注册成功返回的结果
 */
public class RegisterResponse {
    public static final String SUCCESS = "success";
    public static final String Failure = "failure";
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
