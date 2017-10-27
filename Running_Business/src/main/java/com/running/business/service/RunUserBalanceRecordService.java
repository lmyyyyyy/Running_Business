package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserBalanceRecord;

public interface RunUserBalanceRecordService {

	BaseResult<Object> addRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult<Object> updateRunUserBalanceRecord(RunUserBalanceRecord record);
	BaseResult<Object> delRunUserBalanceRecordByID(Integer id);
	BaseResult<Object> delAllRunUserBalanceRecordByUID(Integer uid);
	
	BaseResult<Object> getRunUserBalanceRecordByID(Integer id);
	BaseResult<Object> getAllRunUserBalanceRecordByUID(Integer uid);
}
