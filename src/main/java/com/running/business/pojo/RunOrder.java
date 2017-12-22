package com.running.business.pojo;

import java.util.Date;

public class RunOrder {
    private String orderid;

    private Integer uid;

    private Integer did;

    private String businessName;

    private String businessAddress;

    private String orderGoods;

    private String orderRemark;

    private Double orderPrice;

    private Double orderFee;

    private Double orderTip;

    private Integer orderType;

    private Integer orderRecvAddress;

    private String orderPhone;

    private String orderSpeed;

    private Date orderSendtime;

    private String orderRecvLongitude;

    private String orderRecvLatitude;

    private Date orderTime;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress == null ? null : businessAddress.trim();
    }

    public String getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(String orderGoods) {
        this.orderGoods = orderGoods == null ? null : orderGoods.trim();
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    public Double getOrderTip() {
        return orderTip;
    }

    public void setOrderTip(Double orderTip) {
        this.orderTip = orderTip;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderRecvAddress() {
        return orderRecvAddress;
    }

    public void setOrderRecvAddress(Integer orderRecvAddress) {
        this.orderRecvAddress = orderRecvAddress;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone == null ? null : orderPhone.trim();
    }

    public String getOrderSpeed() {
        return orderSpeed;
    }

    public void setOrderSpeed(String orderSpeed) {
        this.orderSpeed = orderSpeed == null ? null : orderSpeed.trim();
    }

    public Date getOrderSendtime() {
        return orderSendtime;
    }

    public void setOrderSendtime(Date orderSendtime) {
        this.orderSendtime = orderSendtime;
    }

    public String getOrderRecvLongitude() {
        return orderRecvLongitude;
    }

    public void setOrderRecvLongitude(String orderRecvLongitude) {
        this.orderRecvLongitude = orderRecvLongitude == null ? null : orderRecvLongitude.trim();
    }

    public String getOrderRecvLatitude() {
        return orderRecvLatitude;
    }

    public void setOrderRecvLatitude(String orderRecvLatitude) {
        this.orderRecvLatitude = orderRecvLatitude == null ? null : orderRecvLatitude.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}