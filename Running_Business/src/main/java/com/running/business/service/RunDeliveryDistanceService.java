package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryDistance;

public interface RunDeliveryDistanceService {

	BaseResult<Object> addRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult<Object> updateRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult<Object> delRunDeliveryDistanceByDID(Integer did);
	
	BaseResult<Object> getRunDeliveryDistanceByDID(Integer did);
	
}
