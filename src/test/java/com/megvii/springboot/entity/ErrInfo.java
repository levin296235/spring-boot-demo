package com.megvii.springboot.entity;

public class ErrInfo {
    private Integer errType;
    private String node;
    private double errVal;
    private String valUin;
    private Integer errIsFg;
    private String time;
    private Integer isSave;
    private Integer saveType;
    private Integer xh;

    public Integer getErrType() {
        return errType;
    }

    public void setErrType(Integer errType) {
        this.errType = errType;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public double getErrVal() {
        return errVal;
    }

    public void setErrVal(double errVal) {
        this.errVal = errVal;
    }

    public String getValUin() {
        return valUin;
    }

    public void setValUin(String valUin) {
        this.valUin = valUin;
    }

    public Integer getErrIsFg() {
        return errIsFg;
    }

    public void setErrIsFg(Integer errIsFg) {
        this.errIsFg = errIsFg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIsSave() {
        return isSave;
    }

    public void setIsSave(Integer isSave) {
        this.isSave = isSave;
    }

    public Integer getSaveType() {
        return saveType;
    }

    public void setSaveType(Integer saveType) {
        this.saveType = saveType;
    }

    public Integer getXh() {
        return xh;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }
}
