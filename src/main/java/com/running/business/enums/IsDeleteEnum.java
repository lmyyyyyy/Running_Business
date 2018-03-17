package com.running.business.enums;

/**
 * 是否删除枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum IsDeleteEnum {

    DELETE(true, "已删"),
    NOT_DELETE(false, "未删")
    ;

    private Boolean code;

    private String desc;

    IsDeleteEnum(Boolean code, String desc) {
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
    public static IsDeleteEnum getUserTypeEnum(Boolean code) {

        if (code == null) {
            return null;
        }
        IsDeleteEnum returnEnum = null;
        for (IsDeleteEnum userTypeEnum : IsDeleteEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                returnEnum = userTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
