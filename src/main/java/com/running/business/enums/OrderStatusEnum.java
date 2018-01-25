package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-01-20 下午3:13
 */
public enum OrderStatusEnum {

    SUBMMIT(0, "已提交"),
    UNPAID(1, "未支付"),
    PAID(2, "已支付"),
    RECEIVED(3, "已接单"),
    SENDING(4, "配送中"),
    FINISH(5, "已送达"),
    CANCELLED(6, "已取消"),
    REFUND_APPLY(7, "退款申请中"),
    REFUND(8, "已退款")
    ;

    private Integer code;

    private String desc;


    OrderStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static OrderStatusEnum getOrderStatusEnum(Integer code) {

        if (code == null) {
            return null;
        }
        OrderStatusEnum returnEnum = null;
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (code.equals(orderStatusEnum.getCode())) {
                returnEnum = orderStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
