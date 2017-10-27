package com.running.business.pojo;

import java.util.Date;

public class RunDeliveryuser {
    private Integer did;

    private String deliveryUsername;

    private String deliveryPassword;

    private Date deliveryDate;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDeliveryUsername() {
        return deliveryUsername;
    }

    public void setDeliveryUsername(String deliveryUsername) {
        this.deliveryUsername = deliveryUsername == null ? null : deliveryUsername.trim();
    }

    public String getDeliveryPassword() {
        return deliveryPassword;
    }

    public void setDeliveryPassword(String deliveryPassword) {
        this.deliveryPassword = deliveryPassword == null ? null : deliveryPassword.trim();
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}