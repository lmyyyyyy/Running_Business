package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryAddress;

public interface RunDeliveryAddressService {

	BaseResult<Object> addRunDeliveryAddress(RunDeliveryAddress address);
	BaseResult<Object> updateRunDeliveryAddress(RunDeliveryAddress address);
	BaseResult<Object> delRunDeliveryAddressByID(Integer id);
	BaseResult<Object> delAllRunDeliveryAddressByDID(Integer did);
	
	BaseResult<Object> getRunDeliveryAddressByID(Integer id);
	BaseResult<Object> getAllRunDeliveryAddressByDID(Integer did);
}
