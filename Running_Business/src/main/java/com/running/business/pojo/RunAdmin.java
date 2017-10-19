package com.running.business.pojo;

/**
 * 管理员
 * @author Administrator
 *
 */
public class RunAdmin {
	//管理员id
    private Integer adminId;
    //管理员账号
    private String adminUsername;
    //管理员密码
    private String adminPassword;
    //创建时间
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