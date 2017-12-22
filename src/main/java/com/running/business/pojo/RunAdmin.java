package com.running.business.pojo;

public class RunAdmin {
    private Integer adminId;

    private String adminUsername;

    private String adminPassword;

    private String adminTime;

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

    public String getAdminTime() {
        return adminTime;
    }

    public void setAdminTime(String adminTime) {
        this.adminTime = adminTime == null ? null : adminTime.trim();
    }
}