package com.running.business.pojo;

import java.util.Date;

public class RefundRecord {
    private Integer id;

    private Integer uid;

    private Integer did;

    private String orderid;

    private Double amount;

    private String reason;

    private Integer responsibility;

    private Integer source;

    private Integer operator;

    private Date addTime;

    public RefundRecord(Integer id, Integer uid, Integer did, String orderid, Double amount, String reason, Integer responsibility, Integer source, Integer operator, Date addTime) {
        this.id = id;
        this.uid = uid;
        this.did = did;
        this.orderid = orderid;
        this.amount = amount;
        this.reason = reason;
        this.responsibility = responsibility;
        this.source = source;
        this.operator = operator;
        this.addTime = addTime;
    }

    public RefundRecord() {
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

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public Integer getResponsibility() {
        return responsibility;
    }

    public void setResponsibility(Integer responsibility) {
        this.responsibility = responsibility;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
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
}