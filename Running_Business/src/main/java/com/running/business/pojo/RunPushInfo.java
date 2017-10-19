package com.running.business.pojo;

import java.util.Date;

/**
 * 推送消息
 * @author Administrator
 *
 */
public class RunPushInfo {
	//消息id
    private Integer pid;
    //推送标题
    private String pushTitle;
    //推送内容
    private String pushContent;
    //推送图片
    private String pushPhoto;
    //推送状态 0：关闭，1：打开
    private Integer pushStatus;
    //创建时间
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