package com.running.business.pojo;

public class RunUserInfo {
    private Integer uid;

    private String userPhoto;

    private String userName;

    private Boolean userGender;

    private String userPhone;

    private Integer userPoint;

    private String userAddress;

    public RunUserInfo(Integer uid, String userPhoto, String userName, Boolean userGender, String userPhone, Integer userPoint, String userAddress) {
        this.uid = uid;
        this.userPhoto = userPhoto;
        this.userName = userName;
        this.userGender = userGender;
        this.userPhone = userPhone;
        this.userPoint = userPoint;
        this.userAddress = userAddress;
    }

    public RunUserInfo() {
        super();
    }

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
        this.userPhoto = userPhoto == null ? null : userPhoto.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
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
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public Integer getUserPoint() {
        return userPoint;
    }

    public void setUserPoint(Integer userPoint) {
        this.userPoint = userPoint;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }
}