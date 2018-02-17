package com.running.business.enums;

/**
 * @author liumingyu
 * @create 2018-02-02 上午11:28
 */
public enum RefundApplyStatusEnum {

    VERIFYING(0, "审核中"),
    AGREE(1, "同意"),
    REFUSE(2, "拒绝"),
    REFUND_CANCEL(3, "取消退款")

    ;

    private Integer code;

    private String desc;


    RefundApplyStatusEnum(Integer code, String desc) {
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
    public static RefundApplyStatusEnum getRefundApplyStatusEnum(Integer code) {

        if (code == null) {
            return null;
        }
        RefundApplyStatusEnum returnEnum = null;
        for (RefundApplyStatusEnum refundApplyStatusEnum : RefundApplyStatusEnum.values()) {
            if (code.equals(refundApplyStatusEnum.getCode())) {
                returnEnum = refundApplyStatusEnum;
                break;
            }
        }
        return returnEnum;
    }
}
