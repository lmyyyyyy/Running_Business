package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-02-03 下午8:54
 */
public enum CouponStatusEnum {

    UNAVALIBALE(0, "不可用"),
    AVALIBALE(1, "可用"),
    EXPIRE(2, "已过期")
    ;

    private Integer code;

    private String desc;

    CouponStatusEnum(Integer code, String desc) {
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
    public static CouponStatusEnum getCouponStatusEnum(Integer code) {

        if (code == null) {
            return null;
        }
        CouponStatusEnum returnEnum = null;
        for (CouponStatusEnum couponStatusEnum : CouponStatusEnum.values()) {
            if (code.equals(couponStatusEnum.getCode())) {
                returnEnum = couponStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
