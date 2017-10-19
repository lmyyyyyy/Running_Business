package com.running.business.pojo;

/**
 * 配送员信息
 * @author Administrator
 *
 */
public class RunDeliveryInfo {
	//配送员id
    private Integer did;
    //配送员头像
    private String deliveryPhoto;
    //配送员姓名
    private String deliveryName;
    //配送员身份证号
    private String deliveryCard;
    //配送员性别
    private Integer deliveryGender;
    //配送员电话
    private String deliveryPhone;
    //积分
    private Integer deliveryPoint;
    //级数
    private String deliveryLevel;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDeliveryPhoto() {
        return deliveryPhoto;
    }

    public void setDeliveryPhoto(String deliveryPhoto) {
        this.deliveryPhoto = deliveryPhoto == null ? null : deliveryPhoto.trim();
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName == null ? null : deliveryName.trim();
    }

    public String getDeliveryCard() {
        return deliveryCard;
    }

    public void setDeliveryCard(String deliveryCard) {
        this.deliveryCard = deliveryCard == null ? null : deliveryCard.trim();
    }

    public Integer getDeliveryGender() {
        return deliveryGender;
    }

    public void setDeliveryGender(Integer deliveryGender) {
        this.deliveryGender = deliveryGender;
    }

    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    public void setDeliveryPhone(String deliveryPhone) {
        this.deliveryPhone = deliveryPhone == null ? null : deliveryPhone.trim();
    }

    public Integer getDeliveryPoint() {
        return deliveryPoint;
    }

    public void setDeliveryPoint(Integer deliveryPoint) {
        this.deliveryPoint = deliveryPoint;
    }

    public String getDeliveryLevel() {
        return deliveryLevel;
    }

    public void setDeliveryLevel(String deliveryLevel) {
        this.deliveryLevel = deliveryLevel == null ? null : deliveryLevel.trim();
    }
}