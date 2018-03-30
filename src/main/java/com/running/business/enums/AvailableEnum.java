package com.running.business.enums;

/**
 * 是否禁用枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum AvailableEnum {

    CAN(true, "启用"),
    CAN_NOT(false, "禁用")
    ;

    private Boolean code;

    private String desc;

    AvailableEnum(Boolean code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Boolean getCode() {
        return code;
    }

    public void setCode(Boolean code) {
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
    public static AvailableEnum getUserTypeEnum(Boolean code) {

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
