package com.running.business.pojo;

public class RunDeliveryDistance {
    private Integer did;

    private Double sendDistance;

    private Double deliveryDistance;

    private Double viewOrderDistance;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Double getSendDistance() {
        return sendDistance;
    }

    public void setSendDistance(Double sendDistance) {
        this.sendDistance = sendDistance;
    }

    public Double getDeliveryDistance() {
        return deliveryDistance;
    }

    public void setDeliveryDistance(Double deliveryDistance) {
        this.deliveryDistance = deliveryDistance;
    }

    public Double getViewOrderDistance() {
        return viewOrderDistance;
    }

    public void setViewOrderDistance(Double viewOrderDistance) {
        this.viewOrderDistance = viewOrderDistance;
    }
}