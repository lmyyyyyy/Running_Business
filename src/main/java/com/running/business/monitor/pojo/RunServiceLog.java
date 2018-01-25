package com.running.business.monitor.pojo;

import java.util.Date;

public class RunServiceLog {
    private Integer id;

    private String className;

    private String methodName;

    private Long timeCost;

    private Integer operatorId;

    private String operatorName;

    private Integer invokeStatus;

    private Date addTime;

    private Date updateTime;

    private Integer parentId;

    private int mapperStartIndex;

    private int mapperEndIndex;

    private boolean isTop;

    public RunServiceLog(Integer id, String className, String methodName, Long timeCost, Integer operatorId, String operatorName, Integer invokeStatus, Date addTime, Date updateTime, Integer parentId) {
        this.id = id;
        this.className = className;
        this.methodName = methodName;
        this.timeCost = timeCost;
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.invokeStatus = invokeStatus;
        this.addTime = addTime;
        this.updateTime = updateTime;
        this.parentId = parentId;
    }

    public RunServiceLog() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className == null ? null : className.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Long getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Long timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public Integer getInvokeStatus() {
        return invokeStatus;
    }

    public void setInvokeStatus(Integer invokeStatus) {
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }


    public int getMapperStartIndex() {
        return mapperStartIndex;
    }

    public void setMapperStartIndex(int mapperStartIndex) {
        this.mapperStartIndex = mapperStartIndex;
    }

    public int getMapperEndIndex() {
        return mapperEndIndex;
    }

    public void setMapperEndIndex(int mapperEndIndex) {
        this.mapperEndIndex = mapperEndIndex;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }
}