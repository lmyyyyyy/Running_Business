package com.running.business.enums;

/**
 * 用户类型枚举
 *
 * @author liumingyu
 * @create 2018-01-13 下午9:55
 */
public enum UserTypeEnum {

    USER(0, "用户"),
    DELIVERY(1, "配送员"),
    ADMIN(2, "管理员");

    private Integer code;

    private String desc;

    UserTypeEnum(Integer code, String desc) {
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
    public static UserTypeEnum getUserTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        UserTypeEnum returnEnum = null;
        for (UserTypeEnum userTypeEnum : UserTypeEnum.values()) {
            if (code.equals(userTypeEnum.getCode())) {
                returnEnum = userTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
