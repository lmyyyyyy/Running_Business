package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryuser {
    private Integer did;

    private String userphone;

    private String password;

    private Date addTime;

    private Date updateTime;

    private Boolean status;

    private Boolean isDelete;

    public RunDeliveryuser(Integer did, String userphone, String password, Date addTime, Date updateTime, Boolean status, Boolean isDelete) {
        this.did = did;
        this.userphone = userphone;
        this.password = password;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.status = status;
        this.isDelete = isDelete;
    }

    public RunDeliveryuser() {
        super();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}