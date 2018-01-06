package com.running.business.pojo;

import java.util.Date;

public class RunPushInfo {
    private Integer pid;

    private Integer operator;

    private String pushTitle;

    private String pushPhoto;

    private Integer pushStatus;

    private Date pushTime;

    private Date updateTime;

    private Boolean isDelete;

    private String pushContent;

    public RunPushInfo(Integer pid, Integer operator, String pushTitle, String pushPhoto, Integer pushStatus, Date pushTime, Date updateTime, Boolean isDelete, String pushContent) {
        this.pid = pid;
        this.operator = operator;
        this.pushTitle = pushTitle;
        this.pushPhoto = pushPhoto;
        this.pushStatus = pushStatus;
        this.pushTime = pushTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.pushContent = pushContent;
    }

    public RunPushInfo() {
        super();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOperator() {
        return operator;
    }

    public void setOperator(Integer operator) {
        this.operator = operator;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle == null ? null : pushTitle.trim();
    }

    public String getPushPhoto() {
        return pushPhoto;
    }

    public void setPushPhoto(String pushPhoto) {
        this.pushPhoto = pushPhoto == null ? null : pushPhoto.trim();
    }

    public Integer getPushStatus() {
        return pushStatus;
    }

    public void setPushStatus(Integer pushStatus) {
        this.pushStatus = pushStatus;
    }

    public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
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

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent == null ? null : pushContent.trim();
    }
}