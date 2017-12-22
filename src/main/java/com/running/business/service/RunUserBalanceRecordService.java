package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserBalanceRecord;

public interface RunUserBalanceRecordService {

	BaseResult addRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult updateRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult delRunUserBalanceRecordByID(Integer id);
	BaseResult delAllRunUserBalanceRecordByUID(Integer uid);
	
	BaseResult getRunUserBalanceRecordByID(Integer id);
	BaseResult getAllRunUserBalanceRecordByUID(Integer uid);
}
