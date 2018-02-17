package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryBalanceRecord;

public interface RunDeliveryBalanceRecordService {

	/**
	 * 保存配送员余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	void saveRunDeliveryRecord(RunDeliveryBalanceRecord record) throws AppException;

	/**
	 * 更新配送员余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	void updateRunDeliveryRecord(RunDeliveryBalanceRecord record) throws AppException;

	/**
	 * 根据id删除配送员记录
	 *
	 * @param id
	 * @throws AppException
	 */
	void deleteRunDeliveryRecordByID(Integer id) throws AppException;

	/**
	 * 根据配送员id删除所有余额记录
	 *
	 * @param did
	 * @throws AppException
	 */
	void deleteAllRunDeliveryRecordByDID(Integer did) throws AppException;

	/**
	 * 根据id获取配送员余额记录
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	RunDeliveryBalanceRecord getRunDeliveryRecordByID(Integer id) throws AppException;

	/**
	 * 根据配送员id分页获取余额记录
	 *
	 * @param did
	 * @param page
	 * @param size
	 * @param orderField
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	PageInfo<RunDeliveryBalanceRecord> pageAllDeliveryRecordByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException;
}
