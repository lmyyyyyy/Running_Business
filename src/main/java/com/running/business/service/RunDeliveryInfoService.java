package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryInfo;

public interface RunDeliveryInfoService {

	BaseResult addRunDeliveryInfo(RunDeliveryInfo deliveryInfo);
	BaseResult updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo);
	BaseResult delRunDeliveryInfoByID(Integer did);
	
	BaseResult getRunDeliveryInfoByID(Integer did);
}
