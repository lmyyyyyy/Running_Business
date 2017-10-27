package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryuser;

public interface RunDeliveryuserService {

	BaseResult<Object> addRunDeliveryuser(RunDeliveryuser user);
	BaseResult<Object> delRunDeliveryuser(Integer uid);
	BaseResult<Object> updateRunDeliveryuser(RunDeliveryuser user);
	
	BaseResult<Object> checkRunDeliveryuser(String username);
	BaseResult<Object> getRunDeliveryuser(Integer id);
	BaseResult<Object> login(String username, String password);
	BaseResult<Object> getAllRunDeliveryuser();
}
