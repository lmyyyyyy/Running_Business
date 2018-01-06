package com.running.business.pojo;

import java.util.Date;

public class RunUserCoupon {
    private Integer id;

    private Integer uid;

    private String title;

    private String content;

    private String type;

    private Double full;

    private Double subtract;

    private Integer status;

    private Date beginTime;

    private Date expiredTime;

    public RunUserCoupon(Integer id, Integer uid, String title, String content, String type, Double full, Double subtract, Integer status, Date beginTime, Date expiredTime) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.full = full;
        this.subtract = subtract;
        this.status = status;
        this.beginTime = beginTime;
        this.expiredTime = expiredTime;
    }

    public RunUserCoupon() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Double getFull() {
        return full;
    }

    public void setFull(Double full) {
        this.full = full;
    }

    public Double getSubtract() {
        return subtract;
    }

    public void setSubtract(Double subtract) {
        this.subtract = subtract;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }
}