package com.running.business.enums;

/**
 * 是否禁用枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum AvailableEnum {

    NULL(0, "审核中"),
    CAN_NOT(1, "禁用"),
    CAN(2, "启用")
    ;

    private Integer code;

    private String desc;

    AvailableEnum(Integer code, String desc) {
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
    public static AvailableEnum getUserTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        AvailableEnum returnEnum = null;
        for (AvailableEnum userTypeEnum : AvailableEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                returnEnum = userTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
