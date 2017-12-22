package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryAddress;

public interface RunDeliveryAddressService {

	BaseResult addRunDeliveryAddress(RunDeliveryAddress address);
	BaseResult updateRunDeliveryAddress(RunDeliveryAddress address);
	BaseResult delRunDeliveryAddressByID(Integer id);
	BaseResult delAllRunDeliveryAddressByDID(Integer did);
	
	BaseResult getRunDeliveryAddressByID(Integer id);
	BaseResult getAllRunDeliveryAddressByDID(Integer did);
}
