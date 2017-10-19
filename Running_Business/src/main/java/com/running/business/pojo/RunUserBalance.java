package com.running.business.pojo;

/**
 * 用户余额
 * @author Administrator
 *
 */
public class RunUserBalance {
	//用户id
    private Integer uid;
    //用户余额
    private Double userBalance;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(Double userBalance) {
        this.userBalance = userBalance;
    }
}