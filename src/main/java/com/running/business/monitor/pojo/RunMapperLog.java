package com.running.business.monitor.pojo;

import java.util.Date;

public class RunMapperLog {
    private Integer id;

    private Integer serivceLogId;

    private String targetTableName;

    private Byte operateType;

    private Integer influenceRow;

    private Byte invokeStatus;

    private Date addTime;

    private Date updateTime;

    private Long timeCost;

    public RunMapperLog(Integer id, Integer serivceLogId, String targetTableName, Byte operateType, Integer influenceRow, Byte invokeStatus, Date addTime, Date updateTime, Long timeCost) {
        this.id = id;
        this.serivceLogId = serivceLogId;
        this.targetTableName = targetTableName;
        this.operateType = operateType;
        this.influenceRow = influenceRow;
        this.invokeStatus = invokeStatus;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.timeCost = timeCost;
    }

    public RunMapperLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSerivceLogId() {
        return serivceLogId;
    }

    public void setSerivceLogId(Integer serivceLogId) {
        this.serivceLogId = serivceLogId;
    }

    public String getTargetTableName() {
        return targetTableName;
    }

    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName == null ? null : targetTableName.trim();
    }

    public Byte getOperateType() {
        return operateType;
    }

    public void setOperateType(Byte operateType) {
        this.operateType = operateType;
    }

    public Integer getInfluenceRow() {
        return influenceRow;
    }

    public void setInfluenceRow(Integer influenceRow) {
        this.influenceRow = influenceRow;
    }

    public Byte getInvokeStatus() {
        return invokeStatus;
    }

    public void setInvokeStatus(Byte invokeStatus) {
        this.invokeStatus = invokeStatus;
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

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }
}