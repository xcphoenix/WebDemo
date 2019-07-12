package com.lims.model;

import java.util.List;

/**
 * ClassName    LIMS-OrderTime
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/17 下午3:33
 * @version     1.0
 */ 
public class OrderTime {

    private Integer date;
    private List<Integer> stage;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public List<Integer> getStage() {
        return stage;
    }

    public void setStage(List<Integer> stage) {
        this.stage = stage;
    }
}
