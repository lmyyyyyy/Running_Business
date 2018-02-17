package com.running.business.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 帮买物品类型枚举
 *
 * @author liumingyu
 * @create 2018-01-06 下午22:20
 */
public enum HelpBuyGoodsEnum {

    FREE_BUY(0, "随意购"),
    COFFEE(1, "咖啡"),
    SMOKE(2, "烟"),
    DRANK(3, "酒"),
    BREAKFAST(4, "早餐"),
    SUPPER(5, "宵夜"),
    DRUG(6, "药"),
    Fresh(7, "生鲜");

    private Integer code;

    private String desc;

    HelpBuyGoodsEnum(Integer code, String desc) {
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
    public static HelpBuyGoodsEnum getHelpBuyGoodsEnum(Integer code) {

        if (code == null) {
            return null;
        }
        HelpBuyGoodsEnum returnEnum = null;
        for (HelpBuyGoodsEnum helpBuyGoodsEnum : HelpBuyGoodsEnum.values()) {
            if (code.equals(helpBuyGoodsEnum.getDesc())) {
                returnEnum = helpBuyGoodsEnum;
                break;
            }
        }
        return returnEnum;
    }

    /**
     * 取所有desc
     *
     * @return
     */
    public static List<String> getAllGoods() {
        List<String> list = new ArrayList<>();
        for (HelpBuyGoodsEnum helpBuyGoodsEnum : HelpBuyGoodsEnum.values()) {
            if (!"".equals(helpBuyGoodsEnum.getDesc())) {
                list.add(helpBuyGoodsEnum.getDesc());
            }
        }
        return list;
    }
}
