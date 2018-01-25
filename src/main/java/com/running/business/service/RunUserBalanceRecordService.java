package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserBalanceRecord;

public interface RunUserBalanceRecordService {

	BaseResult saveRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult updateRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult deleteRunUserBalanceRecordByID(Integer id);
	BaseResult deleteAllRunUserBalanceRecordByUID(Integer uid);
	
	BaseResult getRunUserBalanceRecordByID(Integer id);
	BaseResult getAllRunUserBalanceRecordByUID(Integer uid);
}
