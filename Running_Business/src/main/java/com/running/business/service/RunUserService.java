package com.running.business.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUser;


public interface RunUserService {
	
	BaseResult<Object> addUser(RunUser user);
	BaseResult<Object> delUser(Integer uid);
	BaseResult<Object> updateUser(RunUser user);
	
	
	BaseResult<Object> checkUser(String username);
	BaseResult<Object> getRunUser(Integer id);
	BaseResult<Object> login(String username, String password, HttpServletRequest request, HttpServletResponse response);
	BaseResult<Object> getAllRunUser();
	
	BaseResult<Object> getUserByToken(String token);
	BaseResult<Object> logout(String token);
	
}
