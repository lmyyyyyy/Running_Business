package com.running.business.pojo;

import java.util.Date;

public class RunOrder {
    private String orderid;

    private Integer uid;

    private Integer did;

    private Integer type;

    private String goods;

    private String sourceAddress;

    private String sourceRemarkAddress;

    private String sourcePhone;

    private String targetAddress;

    private String targetRemarkAddress;

    private String targetPhone;

    private String remark;

    private Date requireTime;

    private Long timeLong;

    private Double amount;

    private Double fee;

    private Double distance;

    private String sourceLongitude;

    private String sourceLatitude;

    private String recvLongitude;

    private String recvLatitude;

    private Integer status;

    private Date addTime;

    private Date updateTime;

    private Date recvTime;

    private String targetTime;

    private Date finishTime;

    public RunOrder(String orderid, Integer uid, Integer did, Integer type, String goods, String sourceAddress, String sourceRemarkAddress, String sourcePhone, String targetAddress, String targetRemarkAddress, String targetPhone, String remark, Date requireTime, Long timeLong, Double amount, Double fee, Double distance, String sourceLongitude, String sourceLatitude, String recvLongitude, String recvLatitude, Integer status, Date addTime, Date updateTime, Date recvTime, String targetTime, Date finishTime) {
        this.orderid = orderid;
        this.uid = uid;
        this.did = did;
        this.type = type;
        this.goods = goods;
        this.sourceAddress = sourceAddress;
        this.sourceRemarkAddress = sourceRemarkAddress;
        this.sourcePhone = sourcePhone;
        this.targetAddress = targetAddress;
        this.targetRemarkAddress = targetRemarkAddress;
        this.targetPhone = targetPhone;
        this.remark = remark;
        this.requireTime = requireTime;
        this.timeLong = timeLong;
        this.amount = amount;
        this.fee = fee;
        this.distance = distance;
        this.sourceLongitude = sourceLongitude;
        this.sourceLatitude = sourceLatitude;
        this.recvLongitude = recvLongitude;
        this.recvLatitude = recvLatitude;
        this.status = status;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.recvTime = recvTime;
        this.targetTime = targetTime;
        this.finishTime = finishTime;
    }

    public RunOrder() {
        super();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods == null ? null : goods.trim();
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress == null ? null : sourceAddress.trim();
    }

    public String getSourceRemarkAddress() {
        return sourceRemarkAddress;
    }

    public void setSourceRemarkAddress(String sourceRemarkAddress) {
        this.sourceRemarkAddress = sourceRemarkAddress == null ? null : sourceRemarkAddress.trim();
    }

    public String getSourcePhone() {
        return sourcePhone;
    }

    public void setSourcePhone(String sourcePhone) {
        this.sourcePhone = sourcePhone == null ? null : sourcePhone.trim();
    }

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress == null ? null : targetAddress.trim();
    }

    public String getTargetRemarkAddress() {
        return targetRemarkAddress;
    }

    public void setTargetRemarkAddress(String targetRemarkAddress) {
        this.targetRemarkAddress = targetRemarkAddress == null ? null : targetRemarkAddress.trim();
    }

    public String getTargetPhone() {
        return targetPhone;
    }

    public void setTargetPhone(String targetPhone) {
        this.targetPhone = targetPhone == null ? null : targetPhone.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getRequireTime() {
        return requireTime;
    }

    public void setRequireTime(Date requireTime) {
        this.requireTime = requireTime;
    }

    public Long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(Long timeLong) {
        this.timeLong = timeLong;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getSourceLongitude() {
        return sourceLongitude;
    }

    public void setSourceLongitude(String sourceLongitude) {
        this.sourceLongitude = sourceLongitude == null ? null : sourceLongitude.trim();
    }

    public String getSourceLatitude() {
        return sourceLatitude;
    }

    public void setSourceLatitude(String sourceLatitude) {
        this.sourceLatitude = sourceLatitude == null ? null : sourceLatitude.trim();
    }

    public String getRecvLongitude() {
        return recvLongitude;
    }

    public void setRecvLongitude(String recvLongitude) {
        this.recvLongitude = recvLongitude == null ? null : recvLongitude.trim();
    }

    public String getRecvLatitude() {
        return recvLatitude;
    }

    public void setRecvLatitude(String recvLatitude) {
        this.recvLatitude = recvLatitude == null ? null : recvLatitude.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getRecvTime() {
        return recvTime;
    }

    public void setRecvTime(Date recvTime) {
        this.recvTime = recvTime;
    }

    public String getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(String targetTime) {
        this.targetTime = targetTime == null ? null : targetTime.trim();
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}