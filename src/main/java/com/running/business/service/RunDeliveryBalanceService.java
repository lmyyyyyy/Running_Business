package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryBalance;

public interface RunDeliveryBalanceService {

	BaseResult addRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult updateRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult delRunDeliveryBalanceByDID(Integer did);
	
	BaseResult getRunDeliveryBalanceByDID(Integer did);
	BaseResult getAllRunDeliveryBalance();
}
