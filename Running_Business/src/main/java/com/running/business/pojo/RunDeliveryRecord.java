package com.running.business.pojo;

import java.util.Date;

/**
 * 配送员提现记录
 * @author Administrator
 *
 */
public class RunDeliveryRecord {
	//配送员id
    private Integer did;
    //原余额
    private Double deliveryOldBalance;
    //提现金额
    private Double deliveryAmount;
    //提现后余额
    private Double deliveryNewBalance;
    //提现方式
    private String deliveryType;
    //提现账号
    private String deliveryNumber;
    //提现时间
    private Date deliveryTime;

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