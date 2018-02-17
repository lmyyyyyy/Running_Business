package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryBalance;

public interface RunDeliveryBalanceService {

	/**
	 * 保存配送员余额
	 *
	 * @param balance
	 * @throws AppException
	 */
	void saveRunDeliveryBalance(RunDeliveryBalance balance) throws AppException;

	/**
	 * 更新配送员余额
	 *
	 * @param balance
	 * @throws AppException
	 */
	void updateRunDeliveryBalance(RunDeliveryBalance balance) throws AppException;

	/**
	 * 根据配送员id删除余额
	 *
	 * @param did
	 * @throws AppException
	 */
	void deleteRunDeliveryBalanceByDID(Integer did) throws AppException;

	/**
	 * 根据配送员id获取余额
	 *
	 * @param did
	 * @return
	 * @throws AppException
	 */
	RunDeliveryBalance getRunDeliveryBalanceByDID(Integer did) throws AppException;

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
	PageInfo<RunDeliveryBalance> pageAllRunDeliveryBalance(Integer page, Integer size, String orderField, String orderType) throws AppException;
}
