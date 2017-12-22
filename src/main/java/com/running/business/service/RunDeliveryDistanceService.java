package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryDistance;

public interface RunDeliveryDistanceService {

	BaseResult addRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult updateRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult delRunDeliveryDistanceByDID(Integer did);
	
	BaseResult getRunDeliveryDistanceByDID(Integer did);
	
}
