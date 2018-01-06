package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryBalance {
    private Integer did;

    private Double deliveryBalance;

    private Date updateTime;

    public RunDeliveryBalance(Integer did, Double deliveryBalance, Date updateTime) {
        this.did = did;
        this.deliveryBalance = deliveryBalance;
        this.updateTime = updateTime;
    }

    public RunDeliveryBalance() {
        super();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Double getDeliveryBalance() {
        return deliveryBalance;
    }

    public void setDeliveryBalance(Double deliveryBalance) {
        this.deliveryBalance = deliveryBalance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}