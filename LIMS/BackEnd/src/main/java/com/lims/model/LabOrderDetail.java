package com.lims.model;

import java.util.List;

/**
 * ClassName    LIMS-OrderDetail
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/17 下午3:32
 * @version     1.0
 */ 
public class LabOrderDetail {

    private int id;
    private List<OrderTime> order;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderTime> getOrder() {
        return order;
    }

    public void setOrder(List<OrderTime> order) {
        this.order = order;
    }
}
