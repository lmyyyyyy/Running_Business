package com.running.business.enums;

/**
 * 帮我 送/取 物品类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午10:42
 */
public enum HelpSendGoodsEnum {

    LIFE_GOODS(0, "生活用品"),
    FILE(1, "文件"),
    FOOD(2, "美食"),
    CAKE(3, "蛋糕"),
    FLOWERS(4, "鲜花"),
    DIGITAL_PRODUCT(5, "数码产品"),
    Other(6, "其他");

    private Integer code;

    private String desc;

    HelpSendGoodsEnum(Integer code, String desc) {
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
    public static HelpSendGoodsEnum getHelpSendGoodsEnum(Integer code) {

        if (code == null) {
            return null;
        }
        HelpSendGoodsEnum returnEnum = null;
        for (HelpSendGoodsEnum helpSendGoodsEnum : HelpSendGoodsEnum.values()) {
            if (code.equals(helpSendGoodsEnum.getDesc())) {
                returnEnum = helpSendGoodsEnum;
                break;
            }
        }
        return returnEnum;
    }
}
