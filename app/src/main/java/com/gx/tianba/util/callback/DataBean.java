package com.gx.tianba.util.callback;

public class DataBean {

    /**
     * message : 登录成功
     * status : 0000
     */
    private Object result;
    private String message;
    private String status;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
