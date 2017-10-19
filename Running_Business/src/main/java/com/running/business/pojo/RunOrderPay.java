package com.running.business.pojo;

import java.util.Date;

/**
 * 订单支付
 * @author Administrator
 *
 */
public class RunOrderPay {
	//用户id
    private Integer uid;
    //订单id
    private Integer orderid;
    //订单总价
    private Double orderPrice;
    //订单小费
    private Double orderTip;
    //订单小费
    private Double orderSubtract;
    //实际价格
    private Double orderActualPrice;
    //方式
    private String orderType;
    //支付时间
    private Date orderTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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