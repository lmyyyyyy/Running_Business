package com.running.business.pojo;

import java.util.Date;

public class RunUserAddress {
    private Integer id;

    private Integer uid;

    private String userAddress;

    private String longitude;

    private String latitude;

    private Date addTime;

    private Date updateTime;

    private Boolean isDelete;

    public RunUserAddress(Integer id, Integer uid, String userAddress, String longitude, String latitude, Date addTime, Date updateTime, Boolean isDelete) {
        this.id = id;
        this.uid = uid;
        this.userAddress = userAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public RunUserAddress() {
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

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}