package com.running.business.pojo;

/**
 * 配送员余额
 * @author Administrator
 *
 */
public class RunDeliveryBalance {
	//配送员id
    private Integer did;
    //配送员余额
    private Double deliveryBalance;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Double getDeliveryBalance() {
        return deliveryBalance;
    }

    public void setDeliveryBalance(Double deliveryBalance) {
        this.deliveryBalance = deliveryBalance;
    }
}