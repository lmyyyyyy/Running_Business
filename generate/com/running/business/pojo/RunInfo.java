package com.running.business.pojo;

import java.util.Date;

public class RunInfo {
    private Integer id;

    private String content;

    private Date infoTime;

    private Date addTime;

    public RunInfo(Integer id, String content, Date infoTime, Date addTime) {
        this.id = id;
        this.content = content;
        this.infoTime = infoTime;
        this.addTime = addTime;
    }

    public RunInfo() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}