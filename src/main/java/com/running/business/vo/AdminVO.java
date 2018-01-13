package com.running.business.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author liumingyu
 * @create 2017-12-24 下午5:08
 */
@Data
public class AdminVO {
    public String adminName;

    public String adminPhone;

    public Date createTime;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
