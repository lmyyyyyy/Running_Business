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

    private String orderType;

    private Date orderTime;

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

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}