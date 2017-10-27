package com.running.business.pojo;

public class RunDeliveryInfo {
    private Integer did;

    private String deliveryPhoto;

    private String deliveryName;

    private String deliveryCard;

    private Integer deliveryGender;

    private String deliveryPhone;

    private String deliveryAddress;

    private Integer deliveryPoint;

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

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
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