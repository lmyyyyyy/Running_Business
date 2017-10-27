package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserInfo;

public interface RunUserInfoService {

	BaseResult<Object> addRunUserInfo(RunUserInfo userInfo);
	BaseResult<Object> updateRunUserInfo(RunUserInfo userInfo);
	BaseResult<Object> delRunUserInfo(Integer uid);
	
	BaseResult<Object> getRunUserInfoById(Integer uid);
	BaseResult<Object> getAllRunUserInfo();
	
}
