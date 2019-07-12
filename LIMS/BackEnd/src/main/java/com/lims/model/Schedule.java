package com.lims.model;

import java.util.Date;

/**
 * ClassName    LIMS-Schedule
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/11 下午12:13
 * @version     1.0
 */ 
public class Schedule {

    private int scheduleId;
    private Date scheduleDate;
    private int scheduleStage;
    private int userId;
    private int labId;
    private String scheduleStatus;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public int getScheduleStage() {
        return scheduleStage;
    }

    public void setScheduleStage(int scheduleStage) {
        this.scheduleStage = scheduleStage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public String getScheduleStatus() {
        return scheduleStatus;
    }

    public void setScheduleStatus(String scheduleStatus) {
        this.scheduleStatus = scheduleStatus;
    }
}
