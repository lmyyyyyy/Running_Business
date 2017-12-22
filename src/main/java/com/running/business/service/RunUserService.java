package com.running.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUser;


public interface RunUserService {
	
	BaseResult addUser(RunUser user) throws AppException;
	BaseResult delUser(Integer uid) throws AppException;
	BaseResult updateUser(RunUser user) throws AppException;
	
	
	BaseResult checkUser(String username) throws AppException;
	boolean checkPwd(String username, String password) throws AppException;
	BaseResult getRunUser(Integer id) throws AppException;
	BaseResult login(String username, String password, HttpServletRequest request, HttpServletResponse response) throws AppException;
	BaseResult getAllRunUser() throws AppException;
	
	BaseResult getUserByToken(String token) throws AppException;
	BaseResult logout(String token) throws AppException;
	
}
