package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryInfo;

public interface RunDeliveryInfoService {

	BaseResult<Object> addRunDeliveryInfo(RunDeliveryInfo deliveryInfo);
	BaseResult<Object> updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo);
	BaseResult<Object> delRunDeliveryInfoByID(Integer did);
	
	BaseResult<Object> getRunDeliveryInfoByID(Integer did);
}
