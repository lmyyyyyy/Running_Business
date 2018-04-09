package com.running.business.enums;

/**
 * 用户性别枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum PersonGenderEnum {

    MAN(true, "男"),
    WOMAN(false, "女")
    ;

    private Boolean code;

    private String desc;

    PersonGenderEnum(Boolean code, String desc) {
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
    public static PersonGenderEnum getUserTypeEnum(Boolean code) {

        if (code == null) {
            return null;
        }
        PersonGenderEnum returnEnum = null;
        for (PersonGenderEnum userTypeEnum : PersonGenderEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                returnEnum = userTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
