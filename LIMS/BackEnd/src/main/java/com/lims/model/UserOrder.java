package com.lims.model;

import java.util.Date;

/**
 * ClassName    LIMS-UserOrder
 * Description  用户预定的实验室信息
 *
 * @author      xuanc
 * @date        2019/6/17 下午2:07
 * @version     1.0
 */ 
public class UserOrder {

    private int labId;
    private int scheduleId;
    private String labName;
    private String labLocale;
    private int labCap;
    private Date orderTime;
    private String scheduleStatus;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabLocale() {
        return labLocale;
    }

    public void setLabLocale(String labLocale) {
        this.labLocale = labLocale;
    }

    public int getLabCap() {
        return labCap;
    }

    public void setLabCap(int labCap) {
        this.labCap = labCap;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
