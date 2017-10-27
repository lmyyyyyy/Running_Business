package com.running.business.pojo;

import java.util.Date;

public class RunUserBalanceRecord {
    private Integer id;

    private Integer uid;

    private Double userOldBalance;

    private Double userAmount;

    private Double userNewBalance;

    private String userType;

    private String userNumber;

    private Date userTime;

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

    public Double getUserOldBalance() {
        return userOldBalance;
    }

    public void setUserOldBalance(Double userOldBalance) {
        this.userOldBalance = userOldBalance;
    }

    public Double getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(Double userAmount) {
        this.userAmount = userAmount;
    }

    public Double getUserNewBalance() {
        return userNewBalance;
    }

    public void setUserNewBalance(Double userNewBalance) {
        this.userNewBalance = userNewBalance;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber == null ? null : userNumber.trim();
    }

    public Date getUserTime() {
        return userTime;
    }

    public void setUserTime(Date userTime) {
        this.userTime = userTime;
    }
}