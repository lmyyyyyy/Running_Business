package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunPushInfo;

public interface RunPushInfoService {
	
	BaseResult saveRunPushInfo(RunPushInfo pushInfo);
	BaseResult updatePushInfo(RunPushInfo pushInfo);
	BaseResult deletePushInfoByID(Integer id);
	
	BaseResult getRunPushInfoByID(Integer id);
	BaseResult getAllRunPushInfo();
	
}
