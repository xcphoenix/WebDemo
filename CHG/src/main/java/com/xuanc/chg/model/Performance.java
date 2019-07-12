package com.xuanc.chg.model;

/**
 * ClassName    chg-Performance
 * Description  演出厅
 *
 * @author      xuanc
 * @date        2019/6/23 下午8:27
 * @version     1.0
 */ 
public class Performance {

    private int performanceId;
    private String performanceName;
    private String performanceDesc;
    private String performanceArea;
    private int performanceRow;
    private int performanceCol;

    public int getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(int performanceId) {
        this.performanceId = performanceId;
    }

    public String getPerformanceName() {
        return performanceName;
    }

    public void setPerformanceName(String performanceName) {
        this.performanceName = performanceName;
    }

    public String getPerformanceDesc() {
        return performanceDesc;
    }

    public void setPerformanceDesc(String performanceDesc) {
        this.performanceDesc = performanceDesc;
    }

    public String getPerformanceArea() {
        return performanceArea;
    }

    public void setPerformanceArea(String performanceArea) {
        this.performanceArea = performanceArea;
    }

    public int getPerformanceRow() {
        return performanceRow;
    }

    public void setPerformanceRow(int performanceRow) {
        this.performanceRow = performanceRow;
    }

    public int getPerformanceCol() {
        return performanceCol;
    }

    public void setPerformanceCol(int performanceCol) {
        this.performanceCol = performanceCol;
    }
}
