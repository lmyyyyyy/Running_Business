package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryDistance;

public interface RunDeliveryDistanceService {

	BaseResult saveRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult updateRunDeliveryDistance(RunDeliveryDistance distance);
	BaseResult deleteRunDeliveryDistanceByDID(Integer did);
	
	BaseResult getRunDeliveryDistanceByDID(Integer did);
	
}
