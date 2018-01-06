package com.running.business.pojo;

import java.util.Date;

public class RunOrderStatus {
    private String orderid;

    private Integer uid;

    private Integer did;

    private Integer orderIsSubmit;

    private Integer orderIsPay;

    private Integer orderIsOrders;

    private Integer orderIsSend;

    private Integer orderIsFinish;

    private Date orderOrdersTime;

    private Date orderFinishTime;

    public RunOrderStatus(String orderid, Integer uid, Integer did, Integer orderIsSubmit, Integer orderIsPay, Integer orderIsOrders, Integer orderIsSend, Integer orderIsFinish, Date orderOrdersTime, Date orderFinishTime) {
        this.orderid = orderid;
        this.uid = uid;
        this.did = did;
        this.orderIsSubmit = orderIsSubmit;
        this.orderIsPay = orderIsPay;
        this.orderIsOrders = orderIsOrders;
        this.orderIsSend = orderIsSend;
        this.orderIsFinish = orderIsFinish;
        this.orderOrdersTime = orderOrdersTime;
        this.orderFinishTime = orderFinishTime;
    }

    public RunOrderStatus() {
        super();
    }

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

    public Integer getOrderIsSubmit() {
        return orderIsSubmit;
    }

    public void setOrderIsSubmit(Integer orderIsSubmit) {
        this.orderIsSubmit = orderIsSubmit;
    }

    public Integer getOrderIsPay() {
        return orderIsPay;
    }

    public void setOrderIsPay(Integer orderIsPay) {
        this.orderIsPay = orderIsPay;
    }

    public Integer getOrderIsOrders() {
        return orderIsOrders;
    }

    public void setOrderIsOrders(Integer orderIsOrders) {
        this.orderIsOrders = orderIsOrders;
    }

    public Integer getOrderIsSend() {
        return orderIsSend;
    }

    public void setOrderIsSend(Integer orderIsSend) {
        this.orderIsSend = orderIsSend;
    }

    public Integer getOrderIsFinish() {
        return orderIsFinish;
    }

    public void setOrderIsFinish(Integer orderIsFinish) {
        this.orderIsFinish = orderIsFinish;
    }

    public Date getOrderOrdersTime() {
        return orderOrdersTime;
    }

    public void setOrderOrdersTime(Date orderOrdersTime) {
        this.orderOrdersTime = orderOrdersTime;
    }

    public Date getOrderFinishTime() {
        return orderFinishTime;
    }

    public void setOrderFinishTime(Date orderFinishTime) {
        this.orderFinishTime = orderFinishTime;
    }
}