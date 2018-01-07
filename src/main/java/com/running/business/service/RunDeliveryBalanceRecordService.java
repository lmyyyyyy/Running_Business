package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryBalanceRecord;

public interface RunDeliveryBalanceRecordService {

	BaseResult addRunDeliveryRecord(RunDeliveryBalanceRecord record);
	BaseResult updateRunDeliveryRecord(RunDeliveryBalanceRecord record);
	BaseResult delRunDeliveryRecordByID(Integer id);
	BaseResult delAllRunDeliveryRecordByDID(Integer did);
	
	BaseResult getRunDeliveryRecordByID(Integer id);
	BaseResult getAllDeliveryRecordByDID(Integer did);
}
