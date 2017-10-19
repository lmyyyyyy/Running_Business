package com.running.business.pojo;

import java.util.Date;

/**
 * 用户-优惠券
 * @author Administrator
 *
 */
public class RunUserCoupon {
	//用户ID
    private Integer uid;
    //优惠券标题
    private String userTitle;
    //优惠券内容
    private String userContent;
    //优惠类型
    private String userType;
    //状态 0：不可用，1：可用
    private Integer userStatus;
    //过期时间
    private Date userExpiredtime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserTitle() {
        return userTitle;
    }

    public void setUserTitle(String userTitle) {
        this.userTitle = userTitle == null ? null : userTitle.trim();
    }

    public String getUserContent() {
        return userContent;
    }

    public void setUserContent(String userContent) {
        this.userContent = userContent == null ? null : userContent.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserExpiredtime() {
        return userExpiredtime;
    }

    public void setUserExpiredtime(Date userExpiredtime) {
        this.userExpiredtime = userExpiredtime;
    }
}