package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryuser;

public interface RunDeliveryuserService {

	BaseResult addRunDeliveryuser(RunDeliveryuser user);
	BaseResult delRunDeliveryuser(Integer uid);
	BaseResult updateRunDeliveryuser(RunDeliveryuser user);
	
	BaseResult checkRunDeliveryuser(String username);
	BaseResult getRunDeliveryuser(Integer id);
	BaseResult login(String username, String password);
	BaseResult getAllRunDeliveryuser();
}
