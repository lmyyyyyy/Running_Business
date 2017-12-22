package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserBalance;

public interface RunUserBalanceService {

	BaseResult addRunUserBalance(RunUserBalance runUserBalance);
	BaseResult updateRunUserBalance(RunUserBalance runUserBalance);

	BaseResult getRunUserBalanceByUID(Integer uid);

}
