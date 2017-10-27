package com.running.business.pojo;

import java.util.Date;

public class RunUserCoupon {
    private Integer id;

    private Integer uid;

    private String userTitle;

    private String userContent;

    private String userType;

    private Double userFull;

    private Double userSubtract;

    private Integer userStatus;

    private Date userExpiredtime;

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

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle == null ? null : userTitle.trim();
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent == null ? null : userContent.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Double getUserFull() {
        return userFull;
    }

    public void setUserFull(Double userFull) {
        this.userFull = userFull;
    }

    public Double getUserSubtract() {
        return userSubtract;
    }

    public void setUserSubtract(Double userSubtract) {
        this.userSubtract = userSubtract;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserExpiredtime() {
        return userExpiredtime;
    }

    public void setUserExpiredtime(Date userExpiredtime) {
        this.userExpiredtime = userExpiredtime;
    }
}