package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryBalanceRecord {
    private Integer id;

    private Integer did;

    private Double oldBalance;

    private Double amount;

    private Double newBalance;

    private Boolean type;

    private String number;

    private Date addTime;

    public RunDeliveryBalanceRecord(Integer id, Integer did, Double oldBalance, Double amount, Double newBalance, Boolean type, String number, Date addTime) {
        this.id = id;
        this.did = did;
        this.oldBalance = oldBalance;
        this.amount = amount;
        this.newBalance = newBalance;
        this.type = type;
        this.number = number;
        this.addTime = addTime;
    }

    public RunDeliveryBalanceRecord() {
        super();
    }

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

    public Double getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Double oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Double newBalance) {
        this.newBalance = newBalance;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}