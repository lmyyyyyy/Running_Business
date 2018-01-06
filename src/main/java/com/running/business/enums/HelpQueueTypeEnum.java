package com.running.business.enums;

/**
 * 代排队类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午10:48
 */
public enum HelpQueueTypeEnum {

    POWERFUL_QUEUE(0, "万能排队"),
    WORK_QUEUE(1, "办事排队"),
    HOSPITAL_QUEUE(2, "医院排队"),
    BANK_QUEUE(3, "银行排队"),
    RESTAURANT_QUEUE(4, "餐厅排队");

    private Integer code;

    private String desc;

    HelpQueueTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;

    }

    /**
     * 根据code获取desc
     *
     * @param code
     * @return
     */
    public static HelpQueueTypeEnum getHelpQueueTypeEnum(Integer code) {

        if (code == null) {
            return null;
        }
        HelpQueueTypeEnum returnEnum = null;
        for (HelpQueueTypeEnum helpQueueTypeEnum : HelpQueueTypeEnum.values()) {
            if (code.equals(helpQueueTypeEnum.getDesc())) {
                returnEnum = helpQueueTypeEnum;
                break;
            }
        }
        return returnEnum;
    }
}
