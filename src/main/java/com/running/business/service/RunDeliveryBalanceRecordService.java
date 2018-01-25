package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryBalanceRecord;

public interface RunDeliveryBalanceRecordService {

	BaseResult saveRunDeliveryRecord(RunDeliveryBalanceRecord record);
	BaseResult updateRunDeliveryRecord(RunDeliveryBalanceRecord record);
	BaseResult deleteRunDeliveryRecordByID(Integer id);
	BaseResult deleteAllRunDeliveryRecordByDID(Integer did);
	
	BaseResult getRunDeliveryRecordByID(Integer id);
	BaseResult getAllDeliveryRecordByDID(Integer did);
}
