package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunInfo;

public interface RunInfoService {

	BaseResult<Object> addRunInfo(RunInfo runInfo);
	BaseResult<Object> updateRunInfo(RunInfo runInfo);
	BaseResult<Object> delRunInfoByID(Integer id);
	BaseResult<Object> delAllRunInfo();
	
	BaseResult<Object> getRunInfoByID(Integer id);
	BaseResult<Object> getAllRunInfo();
	BaseResult<Object> getAllRunInfoByDate(long seconds);
}
