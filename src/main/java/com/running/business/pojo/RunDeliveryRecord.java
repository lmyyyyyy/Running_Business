package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryRecord {
    private Integer id;

    private Integer did;

    private Double deliveryOldBalance;

    private Double deliveryAmount;

    private Double deliveryNewBalance;

    private String deliveryType;

    private String deliveryNumber;

    private Date deliveryTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Double getDeliveryOldBalance() {
        return deliveryOldBalance;
    }

    public void setDeliveryOldBalance(Double deliveryOldBalance) {
        this.deliveryOldBalance = deliveryOldBalance;
    }

    public Double getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(Double deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public Double getDeliveryNewBalance() {
        return deliveryNewBalance;
    }

    public void setDeliveryNewBalance(Double deliveryNewBalance) {
        this.deliveryNewBalance = deliveryNewBalance;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType == null ? null : deliveryType.trim();
    }

    public String getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(String deliveryNumber) {
        this.deliveryNumber = deliveryNumber == null ? null : deliveryNumber.trim();
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
}