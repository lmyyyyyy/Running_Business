package com.running.business.pojo;

public class RunDeliveryInfo {
    private Integer did;

    private String photo;

    private String name;

    private String card;

    private Boolean gender;

    private String phone;

    private String address;

    private Integer point;

    private String level;

    public RunDeliveryInfo(Integer did, String photo, String name, String card, Boolean gender, String phone, String address, Integer point, String level) {
        this.did = did;
        this.photo = photo;
        this.name = name;
        this.card = card;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.point = point;
        this.level = level;
    }

    public RunDeliveryInfo() {
        super();
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }
}