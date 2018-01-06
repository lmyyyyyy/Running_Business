package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryAddress {
    private Integer id;

    private Integer did;

    private String deliveryAddress;

    private String longitude;

    private String latitude;

    private Date addTime;

    private Date updateTime;

    private Boolean isDelete;

    public RunDeliveryAddress(Integer id, Integer did, String deliveryAddress, String longitude, String latitude, Date addTime, Date updateTime, Boolean isDelete) {
        this.id = id;
        this.did = did;
        this.deliveryAddress = deliveryAddress;
        this.longitude = longitude;
        this.latitude = latitude;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public RunDeliveryAddress() {
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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
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