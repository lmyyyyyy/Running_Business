package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryBalance;

public interface RunDeliveryBalanceService {

	BaseResult saveRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult updateRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult deleteRunDeliveryBalanceByDID(Integer did);
	
	BaseResult getRunDeliveryBalanceByDID(Integer did);
	BaseResult getAllRunDeliveryBalance();
}
