package com.lims.model;

import java.util.List;

/**
 * ClassName    LIMS-LabOrder
 * Description  可用的实验室信息
 *
 * @author      xuanc
 * @date        2019/6/15 下午11:25
 * @version     1.0
 */ 
public class LabOrder {

    private int labId;
    private List<Order> orderList;

    public int getLabId() {
        return labId;
    }

    public void setLabId(int labId) {
        this.labId = labId;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
}

