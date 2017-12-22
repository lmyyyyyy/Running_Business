package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserBalanceMapper;
import com.running.business.pojo.RunUserBalance;
import com.running.business.service.RunUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunUserBalanceServiceImpl implements RunUserBalanceService{

	@Autowired
	private RunUserBalanceMapper runUserBalanceMapper;

	@Override
	public BaseResult addRunUserBalance(RunUserBalance runUserBalance) {
		runUserBalanceMapper.insert(runUserBalance);
		return BaseResult.success();
	}

	@Override
	public synchronized BaseResult updateRunUserBalance(RunUserBalance runUserBalance) {
		runUserBalanceMapper.updateByPrimaryKey(runUserBalance);
		return BaseResult.success(runUserBalance);
	}

	@Override
	public BaseResult getRunUserBalanceByUID(Integer uid) {
		RunUserBalance userBalance = runUserBalanceMapper.selectByPrimaryKey(uid);
		if (userBalance == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(userBalance);
	}
	
}
