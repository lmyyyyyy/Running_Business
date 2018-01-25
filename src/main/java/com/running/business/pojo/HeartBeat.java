package com.running.business.pojo;

import java.util.Date;

public class HeartBeat {
    private Integer id;

    private Integer uid;

    private Integer userType;

    private String ip;

    private Date addTime;

    private Date updateTime;

    private String userInfo;

    private String sessionKey;

    public HeartBeat(Integer id, Integer uid, Integer userType, String ip, Date addTime, Date updateTime, String userInfo, String sessionKey) {
        this.id = id;
        this.uid = uid;
        this.userType = userType;
        this.ip = ip;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.userInfo = userInfo;
        this.sessionKey = sessionKey;
    }

    public HeartBeat() {
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
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

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo == null ? null : userInfo.trim();
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey == null ? null : sessionKey.trim();
    }
}