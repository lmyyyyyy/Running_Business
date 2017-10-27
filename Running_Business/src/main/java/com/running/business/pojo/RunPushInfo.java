package com.running.business.pojo;

import java.util.Date;

public class RunPushInfo {
    private Integer pid;

    private String pushTitle;

    private String pushContent;

    private String pushPhoto;

    private Integer pushStatus;

    private Date pushTime;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPushTitle() {
        return pushTitle;
    }

    public void setPushTitle(String pushTitle) {
        this.pushTitle = pushTitle == null ? null : pushTitle.trim();
    }

    public String getPushContent() {
        return pushContent;
    }

    public void setPushContent(String pushContent) {
        this.pushContent = pushContent == null ? null : pushContent.trim();
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
}