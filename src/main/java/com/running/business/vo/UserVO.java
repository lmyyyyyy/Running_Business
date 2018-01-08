package com.running.business.vo;

import lombok.Data;

/**
 * @author liumingyu
 * @create 2017-12-03 下午7:17
 */
@Data
public class UserVO {
    private Integer uid;

    private String userPhoto;

    private String userName;

    private Boolean userGender;

    private String userPhone;

    private Integer userPoint;

    private Integer userAddress;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getUserGender() {
        return userGender;
    }

    public void setUserGender(Boolean userGender) {
        this.userGender = userGender;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public Integer getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(Integer userAddress) {
        this.userAddress = userAddress;
    }
}
