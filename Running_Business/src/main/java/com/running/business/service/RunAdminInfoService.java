package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunAdminInfo;

public interface RunAdminInfoService {

	BaseResult<Object> addRunAdminInfo(RunAdminInfo adminInfo);
	BaseResult<Object> updateRunAdminInfo(RunAdminInfo adminInfo);
	BaseResult<Object> delRunAdminInfoByID(Integer id);
	
	BaseResult<Object> getRunAdminInfoByID(Integer id);
}
