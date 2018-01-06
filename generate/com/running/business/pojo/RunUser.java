package com.running.business.pojo;

import java.util.Date;

public class RunUser {
    private Integer uid;

    private String userphone;

    private String password;

    private Date addTime;

    private Date updateTime;

    private Boolean userStatus;

    private Boolean isDelete;

    public RunUser(Integer uid, String userphone, String password, Date addTime, Date updateTime, Boolean userStatus, Boolean isDelete) {
        this.uid = uid;
        this.userphone = userphone;
        this.password = password;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.userStatus = userStatus;
        this.isDelete = isDelete;
    }

    public RunUser() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public Boolean getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Boolean userStatus) {
        this.userStatus = userStatus;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}