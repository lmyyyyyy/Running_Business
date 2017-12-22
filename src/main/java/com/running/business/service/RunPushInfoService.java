package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunPushInfo;

public interface RunPushInfoService {
	
	BaseResult addRunPushInfo(RunPushInfo pushInfo);
	BaseResult updatePushInfo(RunPushInfo pushInfo);
	BaseResult delPushInfoByID(Integer id);
	
	BaseResult getRunPushInfoByID(Integer id);
	BaseResult getAllRunPushInfo();
	
}
