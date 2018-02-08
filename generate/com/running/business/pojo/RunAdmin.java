package com.running.business.pojo;

import java.util.Date;

public class RunAdmin {
    private Integer adminId;

    private String adminUsername;

    private String adminPassword;

    private Boolean status;

    private Date adminTime;

    private Date updateTime;

    private Boolean isDelete;

    public RunAdmin(Integer adminId, String adminUsername, String adminPassword, Boolean status, Date adminTime, Date updateTime, Boolean isDelete) {
        this.adminId = adminId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.status = status;
        this.adminTime = adminTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
    }

    public RunAdmin() {
        super();
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername == null ? null : adminUsername.trim();
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(Date adminTime) {
        this.adminTime = adminTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }
}