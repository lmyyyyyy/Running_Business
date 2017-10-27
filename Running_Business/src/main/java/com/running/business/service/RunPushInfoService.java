package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunPushInfo;

public interface RunPushInfoService {
	
	BaseResult<Object> addRunPushInfo(RunPushInfo pushInfo);
	BaseResult<Object> updatePushInfo(RunPushInfo pushInfo);
	BaseResult<Object> delPushInfoByID(Integer id);
	
	BaseResult<Object> getRunPushInfoByID(Integer id);
	BaseResult<Object> getAllRunPushInfo();
	
}
