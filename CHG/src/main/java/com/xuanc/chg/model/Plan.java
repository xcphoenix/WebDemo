package com.xuanc.chg.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * ClassName    chg-Plan
 * Description  演出计划
 *
 * @author      xuanc
 * @date        2019/6/24 下午4:57
 * @version     1.0
 */ 
public class Plan {

    private int planId;
    private Integer performanceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date planTime;
    private int planDuration;
    private int playPrice;
    private String planLayout;

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Integer getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(Integer performanceId) {
        this.performanceId = performanceId;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPlanTime() {
        return planTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public int getPlanDuration() {
        return planDuration;
    }

    public void setPlanDuration(int planDuration) {
        this.planDuration = planDuration;
    }

    public int getPlayPrice() {
        return playPrice;
    }

    public void setPlayPrice(int playPrice) {
        this.playPrice = playPrice;
    }

    public String getPlanLayout() {
        return planLayout;
    }

    public void setPlanLayout(String planLayout) {
        this.planLayout = planLayout;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plan plan = (Plan) o;
        return planId == plan.planId &&
                performanceId == plan.performanceId &&
                planDuration == plan.planDuration &&
                playPrice == plan.playPrice &&
                planTime.equals(plan.planTime) &&
                planLayout.equals(plan.planLayout);
    }

    @Override
    public int hashCode() {
        return Objects.hash(planId, performanceId, planTime, planDuration, playPrice, planLayout);
    }
}
