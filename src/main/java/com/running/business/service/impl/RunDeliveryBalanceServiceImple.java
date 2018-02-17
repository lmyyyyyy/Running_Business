package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryBalanceMapper;
import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryBalanceExample;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunDeliveryBalanceServiceImple implements RunDeliveryBalanceService{
	
	@Autowired
	private RunDeliveryBalanceMapper runDeliveryBalanceMapper;

	/**
	 * 保存配送员余额
	 *
	 * @param balance
	 * @throws AppException
	 */
	@Override
	public void saveRunDeliveryBalance(RunDeliveryBalance balance) throws AppException {
		if (balance == null) {
			throw new AppException(ResultEnum.DELIVERY_BALANCE_INFO_EMPTY);
		}
		runDeliveryBalanceMapper.insert(balance);
	}

	/**
	 * 更新配送员余额
	 *
	 * @param balance
	 * @throws AppException
	 */
	@Override
	public void updateRunDeliveryBalance(RunDeliveryBalance balance) throws AppException {
		if (balance == null) {
			throw new AppException(ResultEnum.DELIVERY_BALANCE_INFO_EMPTY);
		}
		balance.setUpdateTime(new Date());
		runDeliveryBalanceMapper.updateByPrimaryKeySelective(balance);
	}

	/**
	 * 根据配送员id删除余额
	 *
	 * @param did
	 * @throws AppException
	 */
	@Override
	public void deleteRunDeliveryBalanceByDID(Integer did) throws AppException {
		RunDeliveryBalance balance = runDeliveryBalanceMapper.selectByPrimaryKey(did);
		if (balance == null) {
			throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryBalanceMapper.deleteByPrimaryKey(did);
	}

	/**
	 * 根据配送员id获取余额
	 *
	 * @param did
	 * @return
	 * @throws AppException
	 */
	@Override
	public RunDeliveryBalance getRunDeliveryBalanceByDID(Integer did) throws AppException {
		RunDeliveryBalance balance = runDeliveryBalanceMapper.selectByPrimaryKey(did);
		if (balance == null) {
			throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return balance;
	}

	/**
	 * 分页获取配送员余额
	 *
	 * @param page
	 * @param size
	 * @param orderField
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	@Override
	public PageInfo<RunDeliveryBalance> pageAllRunDeliveryBalance(Integer page, Integer size, String orderField, String orderType) throws AppException {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (size == null || size <= 0) {
			size = 10;
		}
		if (orderField == null || "".equals(orderField)) {
			orderField = "update_time";
		}
		if (orderType == null || "".equals(orderType)) {
			orderType = "DESC";
		}
		PageHelper.startPage(page, size);
		RunDeliveryBalanceExample example = new RunDeliveryBalanceExample();
		example.setOrderByClause(" " + orderField + " " + orderType);
		List<RunDeliveryBalance> list = runDeliveryBalanceMapper.selectByExample(example);
		return new PageInfo<>(list);
	}
}
