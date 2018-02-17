package com.running.business.enums;

/**
 * 距离时间关系枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum DistanceMinutesRelationEnum {

    ONE(1D, 200D, 10L),
    TWO(201D, 500D, 15L),
    THREE(501D, 1000D, 20L),
    FOUR(1001D, 1500D, 30L),
    FIVE(1501D, 2000D, 40L),
    SIX(2001D, 3000D, 50L),
    SEVEN(3001D, 5000D, 60L)
    ;

    /**
     * 最小距离
     */
    private Double min;

    /**
     * 最大距离
     */
    private Double max;

    /**
     * 需要时间
     */
    private Long ms;

    DistanceMinutesRelationEnum(Double min, Double max, Long ms) {
        this.min = min;
        this.max = max;
        this.ms = ms;
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

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static DistanceMinutesRelationEnum getOrderTypeEnum(Double code) {

        if (code == null) {
            return null;
        }
        DistanceMinutesRelationEnum returnEnum = null;
        for (DistanceMinutesRelationEnum distanceMinutesRelationEnum : DistanceMinutesRelationEnum.values()) {
            if (distanceMinutesRelationEnum.getMin() <= code && code <= distanceMinutesRelationEnum.getMax()) {
                returnEnum = distanceMinutesRelationEnum;
            }
        }
        return returnEnum;
    }
}
