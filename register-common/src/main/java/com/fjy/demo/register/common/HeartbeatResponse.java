package com.fjy.demo.register.common;

public class HeartbeatResponse {
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
