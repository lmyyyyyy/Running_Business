package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunInfo;

public interface RunInfoService {

	BaseResult saveRunInfo(RunInfo runInfo);
	BaseResult updateRunInfo(RunInfo runInfo);
	BaseResult deleteRunInfoByID(Integer id);
	BaseResult deleteAllRunInfo();
	
	BaseResult getRunInfoByID(Integer id);
	BaseResult getAllRunInfo();
	BaseResult getAllRunInfoByDate(long seconds);
}
