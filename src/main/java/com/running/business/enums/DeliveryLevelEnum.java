package com.running.business.enums;

/**
 * 配送员积分称号枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum DeliveryLevelEnum {

    ONE(1, 100, "一条腿"),
    TWO(101, 500, "两条腿"),
    THREE(501, 1000, "三条腿"),
    FOUR(1001, 2000, "四条腿"),
    FIVE(2001, 4000, "五条腿"),
    SIX(4001, 7000, "六条腿"),
    SEVEN(7001, 10000, "飞毛腿")
    ;

    /**
     * 最小积分
     */
    private Integer min;

    /**
     * 最大积分
     */
    private Integer max;

    /**
     * 称号
     */
    private String level;

    DeliveryLevelEnum(Integer min, Integer max, String level) {
        this.min = min;
        this.max = max;
        this.level = level;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static DeliveryLevelEnum getDeliveryLevelEnum(Integer code) {

        if (code == null) {
            return null;
        }
        DeliveryLevelEnum returnEnum = null;
        for (DeliveryLevelEnum deliveryLevelEnum : DeliveryLevelEnum.values()) {
            if (deliveryLevelEnum.getMin() <= code && code <= deliveryLevelEnum.getMax()) {
                returnEnum = deliveryLevelEnum;
            }
        }
        return returnEnum;
    }
}
