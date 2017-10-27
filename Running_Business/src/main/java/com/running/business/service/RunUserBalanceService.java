package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserBalance;

public interface RunUserBalanceService {

	BaseResult<Object> addRunUserBalance(RunUserBalance runUserBalance);
	BaseResult<Object> updateRunUserBalance(RunUserBalance runUserBalance);

	BaseResult<Object> getRunUserBalanceByUID(Integer uid);

}
