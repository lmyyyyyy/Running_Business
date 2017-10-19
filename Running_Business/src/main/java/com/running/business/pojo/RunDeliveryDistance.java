package com.running.business.pojo;

/**
 * 配送员配置抢单，派单信息
 * @author Administrator
 *
 */
public class RunDeliveryDistance {
	//配送员id
    private Integer did;
    //配送距离
    private Double sendDistance;
    //派单距离
    private Double deliveryDistance;
    //可见订单距离
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