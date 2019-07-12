package com.lims.model;

/**
 * ClassName    backEnd-Result
 * Description  消息
 *
 * @author      xuanc
 * @date        19-5-31 下午8:10
 * @version     1.0
 */ 
public class Result {

    private int status;
    private String msg;
    private Object data;
    private Boolean succ;

    public Result() {}

    public Result(int status, String msg, Object data, Boolean succ) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.succ = succ;
    }

    public Boolean getSucc() {
        return succ;
    }

    public void setSucc(Boolean succ) {
        this.succ = succ;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
