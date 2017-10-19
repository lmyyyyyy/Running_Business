package com.running.business.pojo;

/**
 * 用户-我的偏好
 * @author Administrator
 *
 */
public class RunUserPreference {
	//用户id
    private Integer uid;
	//偏好类型
    private String userGoodstype;
    //商品
    private String userGoods;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserGoodstype() {
        return userGoodstype;
    }

    public void setUserGoodstype(String userGoodstype) {
        this.userGoodstype = userGoodstype == null ? null : userGoodstype.trim();
    }

    public String getUserGoods() {
        return userGoods;
    }

    public void setUserGoods(String userGoods) {
        this.userGoods = userGoods == null ? null : userGoods.trim();
    }
}