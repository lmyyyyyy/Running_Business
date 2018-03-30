package com.running.business.enums;

/**
 * 用户状态枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum PersonStatusEnum {

    ONLINE(true, "在线"),
    NOT_ONLINE(false, "离线")
    ;

    private Boolean code;

    private String desc;

    PersonStatusEnum(Boolean code, String desc) {
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
    public static PersonStatusEnum getUserTypeEnum(Boolean code) {

        if (code == null) {
            return null;
        }
        PersonStatusEnum returnEnum = null;
        for (PersonStatusEnum userTypeEnum : PersonStatusEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                returnEnum = userTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
