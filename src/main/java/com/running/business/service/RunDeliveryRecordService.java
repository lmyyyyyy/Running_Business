package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryRecord;

public interface RunDeliveryRecordService {

	BaseResult addRunDeliveryRecord(RunDeliveryRecord record);
	BaseResult updateRunDeliveryRecord(RunDeliveryRecord record);
	BaseResult delRunDeliveryRecordByID(Integer id);
	BaseResult delAllRunDeliveryRecordByDID(Integer did);
	
	BaseResult getRunDeliveryRecordByID(Integer id);
	BaseResult getAllDeliveryRecordByDID(Integer did);
}
