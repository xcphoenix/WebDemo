package com.lims.model;

/**
 * ClassName    LIMS-Laboratory
 * Description  
 *
 * @author      xuanc
 * @date        2019/6/11 下午12:04
 * @version     1.0
 */ 
public class Laboratory {
    private int labId;
    private String labName;
    private String labType;
    private String labDesc;
    private String labLocale;
    private int labCap;
    private String xz;

    public Laboratory() {}

    public Laboratory(int labId, String labName, String labType, String labDesc, String labLocale, int labCap) {
        this.labId = labId;
        this.labName = labName;
        this.labType = labType;
        this.labDesc = labDesc;
        this.labLocale = labLocale;
        this.labCap = labCap;
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

    public String getLabType() {
        return labType;
    }

    public void setLabType(String labType) {
        this.labType = labType;
    }

    public String getLabDesc() {
        return labDesc;
    }

    public void setLabDesc(String labDesc) {
        this.labDesc = labDesc;
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

    public String getXz() {
        return xz;
    }

    public void setXz(String xz) {
        this.xz = xz;
    }
}
