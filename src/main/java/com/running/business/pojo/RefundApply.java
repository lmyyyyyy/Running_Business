package com.running.business.pojo;

import java.util.Date;

public class RefundApply {
    private Integer id;

    private Integer uid;

    private String orderid;

    private Double amount;

    private String reason;

    private Integer status;

    private Integer operator;

    private Date addTime;

    private Date updateTime;

    public RefundApply(Integer id, Integer uid, String orderid, Double amount, String reason, Integer status, Integer operator, Date addTime, Date updateTime) {
        this.id = id;
        this.uid = uid;
        this.orderid = orderid;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.operator = operator;
        this.addTime = addTime;
        this.updateTime = updateTime;
    }

    public RefundApply() {
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}