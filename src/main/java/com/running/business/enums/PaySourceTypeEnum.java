package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-01-30 下午6:36
 */
public enum PaySourceTypeEnum {

    ARRIVE(0, "到付"),
    BALANCE(1, "余额"),
    ALIPAY(2, "支付宝"),
    WECHATPAY(3, "微信"),
    APPLEPAY(4, "苹果支付"),
    BANK(5, "银行卡")
    ;

    private Integer code;

    private String desc;


    PaySourceTypeEnum(Integer code, String desc) {
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
    public static PaySourceTypeEnum getOrderPayTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        PaySourceTypeEnum returnEnum = null;
        for (PaySourceTypeEnum paySourceTypeEnum : PaySourceTypeEnum.values()) {
            if (code.equals(paySourceTypeEnum.getCode())) {
                returnEnum = paySourceTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
