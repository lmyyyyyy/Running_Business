package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunAdminInfo;

public interface RunAdminInfoService {

	BaseResult addRunAdminInfo(RunAdminInfo adminInfo);
	BaseResult updateRunAdminInfo(RunAdminInfo adminInfo);
	BaseResult delRunAdminInfoByID(Integer id);
	
	BaseResult getRunAdminInfoByID(Integer id);
}
