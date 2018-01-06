package com.running.business.pojo;

import java.util.Date;

public class RunUserBalance {
    private Integer uid;

    private Double userBalance;

    private Date updateTime;

    public RunUserBalance(Integer uid, Double userBalance, Date updateTime) {
        this.uid = uid;
        this.userBalance = userBalance;
        this.updateTime = updateTime;
    }

    public RunUserBalance() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}