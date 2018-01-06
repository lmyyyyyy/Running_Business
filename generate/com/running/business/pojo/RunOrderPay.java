package com.running.business.pojo;

import java.util.Date;

public class RunOrderPay {
    private Integer id;

    private Integer uid;

    private String orderid;

    private Double orderPrice;

    private Double orderTip;

    private Double orderSubtract;

    private Double orderActualPrice;

    private Integer payType;

    private Date payTime;

    public RunOrderPay(Integer id, Integer uid, String orderid, Double orderPrice, Double orderTip, Double orderSubtract, Double orderActualPrice, Integer payType, Date payTime) {
        this.id = id;
        this.uid = uid;
        this.orderid = orderid;
        this.orderPrice = orderPrice;
        this.orderTip = orderTip;
        this.orderSubtract = orderSubtract;
        this.orderActualPrice = orderActualPrice;
        this.payType = payType;
        this.payTime = payTime;
    }

    public RunOrderPay() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderTip() {
        return orderTip;
    }

    public void setOrderTip(Double orderTip) {
        this.orderTip = orderTip;
    }

    public Double getOrderSubtract() {
        return orderSubtract;
    }

    public void setOrderSubtract(Double orderSubtract) {
        this.orderSubtract = orderSubtract;
    }

    public Double getOrderActualPrice() {
        return orderActualPrice;
    }

    public void setOrderActualPrice(Double orderActualPrice) {
        this.orderActualPrice = orderActualPrice;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}