package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunDeliveryBalance;

public interface RunDeliveryBalanceService {

	BaseResult<Object> addRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult<Object> updateRunDeliveryBalance(RunDeliveryBalance balance);
	BaseResult<Object> delRunDeliveryBalanceByDID(Integer did);
	
	BaseResult<Object> getRunDeliveryBalanceByDID(Integer did);
	BaseResult<Object> getAllRunDeliveryBalance();
}
