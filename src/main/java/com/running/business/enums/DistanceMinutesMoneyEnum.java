package com.running.business.enums;

/**
 * 距离时间配送费枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum DistanceMinutesMoneyEnum {

    ONE(1D, 200D, 10L, 3),
    TWO(201D, 500D, 15L, 3),
    THREE(501D, 1000D, 20L, 4),
    FOUR(1001D, 1500D, 30L, 4),
    FIVE(1501D, 2000D, 40L, 5),
    SIX(2001D, 3000D, 50L, 5),
    SEVEN(3001D, 5000D, 60L, 6),
    TOP(5001D, 20000D, 120L, 50)
    ;

    /**
     * 最小距离 单位：m
     */
    private Double min;

    /**
     * 最大距离 单位：m
     */
    private Double max;

    /**
     * 需要时间 单位：分钟
     */
    private Long ms;

    /**
     * 配送费
     */
    private Integer money;

    DistanceMinutesMoneyEnum(Double min, Double max, Long ms, Integer money) {
        this.min = min;
        this.max = max;
        this.ms = ms;
        this.money = money;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Long getMs() {
        return ms * 1000 * 60;
    }

    public void setMs(Long ms) {
        this.ms = ms;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static DistanceMinutesMoneyEnum getOrderTypeEnum(Double code) {

        if (code == null) {
            return null;
        }
        DistanceMinutesMoneyEnum returnEnum = null;
        for (DistanceMinutesMoneyEnum distanceMinutesRelationEnum : DistanceMinutesMoneyEnum.values()) {
            if (distanceMinutesRelationEnum.getMin() <= code && code <= distanceMinutesRelationEnum.getMax()) {
                returnEnum = distanceMinutesRelationEnum;
            }
        }
        return returnEnum;
    }

    /**
     * 根据ms获取desc
     *
     * @param ms
     * @return
     */
    public static DistanceMinutesMoneyEnum getDistanceEnumByMs(Long ms) {

        if (ms == null) {
            return null;
        }
        DistanceMinutesMoneyEnum returnEnum = null;
        for (DistanceMinutesMoneyEnum distanceMinutesRelationEnum : DistanceMinutesMoneyEnum.values()) {
            if (ms.equals(distanceMinutesRelationEnum.getMs())) {
                returnEnum = distanceMinutesRelationEnum;
            }
        }
        return returnEnum;
    }
}
