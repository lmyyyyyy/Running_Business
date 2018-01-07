package com.running.business.pojo;

import java.util.Date;

public class ReportRecord {
    private Integer id;

    private Integer uid;

    private Integer did;

    private Integer activeSide;

    private String reason;

    private Integer level;

    private Date addTime;

    public ReportRecord(Integer id, Integer uid, Integer did, Integer activeSide, String reason, Integer level, Date addTime) {
        this.id = id;
        this.uid = uid;
        this.did = did;
        this.activeSide = activeSide;
        this.reason = reason;
        this.level = level;
        this.addTime = addTime;
    }

    public ReportRecord() {
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

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getActiveSide() {
        return activeSide;
    }

    public void setActiveSide(Integer activeSide) {
        this.activeSide = activeSide;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}