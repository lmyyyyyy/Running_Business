package com.running.business.pojo;

import java.util.Date;

/**
 * 订单信息表
 * @author Administrator
 *
 */
public class RunOrder {
	//订单号
    private Integer orderid;
    //用户id
    private Integer uid;
    //配送员id
    private Integer did;
    //商家名称
    private String businessName;
    //商家地址
    private String businessAddress;
    //商品
    private String orderGoods;
    //备注
    private String orderRemark;
    //价格
    private Double orderPrice;
    //配送费
    private Double orderFee;
    //消费
    private Double orderTip;
    //支付方式 0：在线；1：到付
    private Integer orderType;
    //收货地址
    private Integer orderRecvAddress;
    //联系方式
    private String orderPhone;
    //配置速度
    private String orderSpeed;
    //配送时间
    private Date orderSendtime;
    //经度
    private String orderRecvLongitude;
    //纬度
    private String orderRecvLatitude;
    //订单时间
    private Date orderTime;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName == null ? null : businessName.trim();
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress == null ? null : businessAddress.trim();
    }

    public String getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(String orderGoods) {
        this.orderGoods = orderGoods == null ? null : orderGoods.trim();
    }

    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark == null ? null : orderRemark.trim();
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Double getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(Double orderFee) {
        this.orderFee = orderFee;
    }

    public Double getOrderTip() {
        return orderTip;
    }

    public void setOrderTip(Double orderTip) {
        this.orderTip = orderTip;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderRecvAddress() {
        return orderRecvAddress;
    }

    public void setOrderRecvAddress(Integer orderRecvAddress) {
        this.orderRecvAddress = orderRecvAddress;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone == null ? null : orderPhone.trim();
    }

    public String getOrderSpeed() {
        return orderSpeed;
    }

    public void setOrderSpeed(String orderSpeed) {
        this.orderSpeed = orderSpeed == null ? null : orderSpeed.trim();
    }

    public Date getOrderSendtime() {
        return orderSendtime;
    }

    public void setOrderSendtime(Date orderSendtime) {
        this.orderSendtime = orderSendtime;
    }

    public String getOrderRecvLongitude() {
        return orderRecvLongitude;
    }

    public void setOrderRecvLongitude(String orderRecvLongitude) {
        this.orderRecvLongitude = orderRecvLongitude == null ? null : orderRecvLongitude.trim();
    }

    public String getOrderRecvLatitude() {
        return orderRecvLatitude;
    }

    public void setOrderRecvLatitude(String orderRecvLatitude) {
        this.orderRecvLatitude = orderRecvLatitude == null ? null : orderRecvLatitude.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }
}