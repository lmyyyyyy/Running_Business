package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-02-02 下午2:36
 */
public enum ReportLevelEnum {

    ONE(1, "有瑕疵"),
    TWO(2, "比较过分"),
    THREE(3, "过分"),
    FOUR(4, "很过分"),
    FIVE(5, "超级过分"),
    SIX(6, "杀千刀的");

    private Integer code;

    private String desc;


    ReportLevelEnum(Integer code, String desc) {
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
    public static ReportLevelEnum getReportLevelEnum(Integer code) {

        if (code == null) {
            return null;
        }
        ReportLevelEnum returnEnum = null;
        for (ReportLevelEnum reportLevelEnum : ReportLevelEnum.values()) {
            if (code.equals(reportLevelEnum.getCode())) {
                returnEnum = reportLevelEnum;
                break;
            }
        }
        return returnEnum;
    }
}
