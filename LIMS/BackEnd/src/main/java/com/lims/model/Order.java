package com.lims.model;

import java.util.Date;
import java.util.Objects;

/**
 * ClassName    LIMS-Order
 * Description  预定单
 *
 * @author      xuanc
 * @date        2019/6/16 上午10:24
 * @version     1.0
 */
public class Order {
    private Integer oLabId;
    private Long oUserId;
    private int date;
    private int stage;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Order)) {
            return false;
        }
        Order order = (Order)obj;
        return order.getDate() == this.date
                && order.getStage() == this.stage
                && (order.getoUserId() == this.oUserId)
                && (order.getoLabId() == (this.oLabId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(oLabId, oUserId, date, stage);
    }

    public Integer getoLabId() {
        return oLabId;
    }

    public void setoLabId(Integer oLabId) {
        this.oLabId = oLabId;
    }

    public Long getoUserId() {
        return oUserId;
    }

    public void setoUserId(Long oUserId) {
        this.oUserId = oUserId;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }
}
