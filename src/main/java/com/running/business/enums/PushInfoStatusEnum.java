package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-02-03 下午8:54
 */
public enum PushInfoStatusEnum {

    UNAVALIBALE(0, "不推送"),
    AVALIBALE(1, "推送")
    ;

    private Integer code;

    private String desc;

    PushInfoStatusEnum(Integer code, String desc) {
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
    public static PushInfoStatusEnum getPushInfoStatusEnum(Integer code) {

        if (code == null) {
            return null;
        }
        PushInfoStatusEnum returnEnum = null;
        for (PushInfoStatusEnum pushInfoStatusEnum : PushInfoStatusEnum.values()) {
            if (code.equals(pushInfoStatusEnum.getCode())) {
                returnEnum = pushInfoStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
