package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-01-30 下午6:36
 */
public enum OrderPayTypeEnum {

    ONLINE_PAY(1, "在线付"),
    ARRIVE_PAY(2, "到付")
    ;

    private Integer code;

    private String desc;


    OrderPayTypeEnum(Integer code, String desc) {
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
    public static OrderPayTypeEnum getOrderPayTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        OrderPayTypeEnum returnEnum = null;
        for (OrderPayTypeEnum orderPayTypeEnum : OrderPayTypeEnum.values()) {
            if (code.equals(orderPayTypeEnum.getCode())) {
                returnEnum = orderPayTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
