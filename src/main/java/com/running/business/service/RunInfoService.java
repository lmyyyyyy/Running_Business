package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunInfo;

public interface RunInfoService {

	BaseResult addRunInfo(RunInfo runInfo);
	BaseResult updateRunInfo(RunInfo runInfo);
	BaseResult delRunInfoByID(Integer id);
	BaseResult delAllRunInfo();
	
	BaseResult getRunInfoByID(Integer id);
	BaseResult getAllRunInfo();
	BaseResult getAllRunInfoByDate(long seconds);
}
