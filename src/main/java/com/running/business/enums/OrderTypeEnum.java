package com.running.business.enums;

/**
 * 订单类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum OrderTypeEnum {

    HELP_BUY(1, "帮我买", "HelpBuyOrderService", "saveHelpBuyOrder"),
    HELP_SEND(2, "帮我送", "HelpSendOrderService", "saveHelpSendOrder"),
    HELP_GET(3, "帮我取", "HelpGetOrderService", "saveHelpGetOrder"),
    HELP_QUEUE(4, "代排队", "HelpQueueOrderService", "saveHelpQueueOrder");


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
