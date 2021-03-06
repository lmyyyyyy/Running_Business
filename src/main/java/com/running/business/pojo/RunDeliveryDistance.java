package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryDistance {
    private Integer did;

    private Double sendDistance;

    private Double deliveryDistance;

    private Double viewOrderDistance;

    private Date updateTime;

    public RunDeliveryDistance(Integer did, Double sendDistance, Double deliveryDistance, Double viewOrderDistance, Date updateTime) {
        this.did = did;
        this.sendDistance = sendDistance;
        this.deliveryDistance = deliveryDistance;
        this.viewOrderDistance = viewOrderDistance;
        this.updateTime = updateTime;
    }

    public RunDeliveryDistance() {
        super();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Double getSendDistance() {
        return sendDistance;
    }

    public void setSendDistance(Double sendDistance) {
        this.sendDistance = sendDistance;
    }

    public Double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(Double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public Double getViewOrderDistance() {
        return viewOrderDistance;
    }

    public void setViewOrderDistance(Double viewOrderDistance) {
        this.viewOrderDistance = viewOrderDistance;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}