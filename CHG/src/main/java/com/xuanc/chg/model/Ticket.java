package com.xuanc.chg.model;

import java.util.Date;

/**
 * ClassName    chg-Ticket
 * Description  票
 *
 * @author      xuanc
 * @date        2019/6/25 上午12:02
 * @version     1.0
 */ 
public class Ticket {

    private int ticketId;
    private int userId;
    private int planId;
    private Date ticketTime;
    private int playPrice;
    private Date planTime;
    private int seatRow;
    private int seatCol;
    private String performanceName;
    private String performanceArea;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public Date getTicketTime() {
        return ticketTime;
    }

    public void setTicketTime(Date ticketTime) {
        this.ticketTime = ticketTime;
    }

    public int getPlayPrice() {
        return playPrice;
    }

    public void setPlayPrice(int playPrice) {
        this.playPrice = playPrice;
    }

    public Date getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Date planTime) {
        this.planTime = planTime;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public int getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(int seatCol) {
        this.seatCol = seatCol;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public String getPerformanceArea() {
        return performanceArea;
    }

    public void setPerformanceArea(String performanceArea) {
        this.performanceArea = performanceArea;
    }
}
