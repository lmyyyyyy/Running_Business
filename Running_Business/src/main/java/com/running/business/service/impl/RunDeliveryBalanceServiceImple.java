package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryBalanceMapper;
import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryBalanceExample;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.util.ValidateUtil;

@Service
public class RunDeliveryBalanceServiceImple implements RunDeliveryBalanceService{
	
	@Autowired
	private RunDeliveryBalanceMapper runDeliveryBalanceMapper;

	@Override
	public BaseResult<Object> addRunDeliveryBalance(RunDeliveryBalance balance) {
		runDeliveryBalanceMapper.insert(balance);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunDeliveryBalance(
			RunDeliveryBalance balance) {
		runDeliveryBalanceMapper.updateByPrimaryKey(balance);
		return BaseResult.success(balance);
	}

	@Override
	public BaseResult<Object> delRunDeliveryBalanceByDID(Integer did) {
		RunDeliveryBalance balance = runDeliveryBalanceMapper.selectByPrimaryKey(did);
		if (balance == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runDeliveryBalanceMapper.deleteByPrimaryKey(did);
		return BaseResult.success(balance);
	}

	@Override
	public BaseResult<Object> getRunDeliveryBalanceByDID(Integer did) {
		RunDeliveryBalance balance = runDeliveryBalanceMapper.selectByPrimaryKey(did);
		if (balance == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(balance);
	}

	@Override
	public BaseResult<Object> getAllRunDeliveryBalance() {
		RunDeliveryBalanceExample example = new RunDeliveryBalanceExample();
		List<RunDeliveryBalance> list = runDeliveryBalanceMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
