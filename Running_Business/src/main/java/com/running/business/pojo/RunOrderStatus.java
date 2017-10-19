package com.running.business.pojo;

import java.util.Date;

/**
 * 订单状态
 * @author Administrator
 *
 */
public class RunOrderStatus {
	//订单id
    private Integer orderid;
    //用户id
    private Integer uid;
    //配送员id
    private Integer did;
    //是否确认订单 0：是； 1：否
    private Integer orderIsSubmit;
    //是否支付 0：是； 1：否
    private Integer orderIsPay;
    //是否接单 0：是； 1：否
    private Integer orderIsOrders;
    //是否派送 0：到达商家； 1：买到；2：配送中
    private Integer orderIsSend;
    //是否完成 0：是； 1：否
    private Integer orderIsFinish;
    //接单时间 
    private Date orderOrdersTime;
    //送达时间
    private Date orderFinishTime;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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