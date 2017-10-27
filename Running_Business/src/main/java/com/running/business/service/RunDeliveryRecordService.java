package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryRecord;

public interface RunDeliveryRecordService {

	BaseResult<Object> addRunDeliveryRecord(RunDeliveryRecord record);
	BaseResult<Object> updateRunDeliveryRecord(RunDeliveryRecord record);
	BaseResult<Object> delRunDeliveryRecordByID(Integer id);
	BaseResult<Object> delAllRunDeliveryRecordByDID(Integer did);
	
	BaseResult<Object> getRunDeliveryRecordByID(Integer id);
	BaseResult<Object> getAllDeliveryRecordByDID(Integer did);
}
