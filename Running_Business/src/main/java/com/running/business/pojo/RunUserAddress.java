package com.running.business.pojo;

/**
 * 用户地址
 * @author Administrator
 *
 */
public class RunUserAddress {
	//用户id
    private Integer uid;
    //用户地址
    private String userAddress;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }
}