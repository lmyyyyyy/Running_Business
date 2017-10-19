package com.running.business.pojo;

import java.util.Date;

/**
 * 用户账号
 * @author Administrator
 *
 */
public class RunUser {
	//用户id
    private Integer uid;
    //用户账号
    private String userUsername;
    //用户密码
    private String userPassword;
    //创建时间
    private Date userDate;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUserUsername() {
        return userUsername;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername == null ? null : userUsername.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Date getUserDate() {
        return userDate;
    }

    public void setUserDate(Date userDate) {
        this.userDate = userDate;
    }
}