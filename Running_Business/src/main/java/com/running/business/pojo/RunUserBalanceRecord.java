package com.running.business.pojo;

import java.util.Date;

/**
 * 用户充值记录
 * @author Administrator
 *
 */
public class RunUserBalanceRecord {
	//用户id
    private Integer uid;
    //原余额
    private Double userOldBalance;
    //充值金额
    private Double userAmount;
    //充值后余额
    private Double userNewBalance;
    //充值方式
    private String userType;
    //充值账号
    private String userNumber;
    //充值时间
    private Date userTime;

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