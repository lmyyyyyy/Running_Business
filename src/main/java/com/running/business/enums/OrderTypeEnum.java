package com.running.business.enums;

/**
 * 订单类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum OrderTypeEnum {

    HELP_BUY(0, "帮我买"),
    HELP_SEND(1, "帮我送"),
    HELP_GET(2, "帮我取"),
    HELP_QUEUE(3, "代排队");

    private Integer code;

    private String desc;

    OrderTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;

    }

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static OrderTypeEnum getOrderTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        OrderTypeEnum returnEnum = null;
        for (OrderTypeEnum orderTypeEnum : OrderTypeEnum.values()) {
            if (code.equals(orderTypeEnum.getDesc())) {
                returnEnum = orderTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
