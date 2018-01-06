package com.running.business.pojo;

public class RunUserPreference {
    private Integer id;

    private Integer uid;

    private String userGoodstype;

    private String userGoods;

    private Integer times;

    public RunUserPreference(Integer id, Integer uid, String userGoodstype, String userGoods, Integer times) {
        this.id = id;
        this.uid = uid;
        this.userGoodstype = userGoodstype;
        this.userGoods = userGoods;
        this.times = times;
    }

    public RunUserPreference() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }
}