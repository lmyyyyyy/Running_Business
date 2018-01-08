package com.running.business.enums;

/**
 * 订单类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum OrderTypeEnum {

    HELP_BUY(0, "帮我买", "com.running.business.service.RunOrderService", "helpBuyOrder"),
    HELP_SEND(1, "帮我送", "com.running.business.service.RunOrderService", "helpSendOrder"),
    HELP_GET(2, "帮我取", "com.running.business.service.RunOrderService", "helpGetOrder"),
    HELP_QUEUE(3, "代排队", "com.running.business.service.RunOrderService", "helpQueueOrder");


    private Integer code;

    private String desc;

    private String className;

    private String methodName;

    OrderTypeEnum(Integer code, String desc, String className, String methodName) {
        this.code = code;
        this.desc = desc;
        this.className = className;
        this.methodName = methodName;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
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
            if (code.equals(orderTypeEnum.getCode())) {
                returnEnum = orderTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
