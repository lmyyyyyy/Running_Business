package com.running.business.pojo;

/**
 * 管理员信息
 * @author Administrator
 *
 */
public class RunAdminInfo {
	//管理员id
    private Integer adminId;
    //管理员姓名
    private String adminName;
    //管理员电话
    private String adminPhone;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }
}