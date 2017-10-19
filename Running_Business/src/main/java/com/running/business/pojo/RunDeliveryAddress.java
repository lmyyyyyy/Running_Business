package com.running.business.pojo;

/**
 * 配送员地址
 * @author Administrator
 *
 */
public class RunDeliveryAddress {
	//配送员id
    private Integer did;
    //配送员地址
    private String deliveryAddress;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }
}