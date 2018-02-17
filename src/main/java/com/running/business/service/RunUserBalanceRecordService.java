package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserBalanceRecord;

public interface RunUserBalanceRecordService {

	/**
	 * 保存用户余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	void saveRunUserBalanceRecord(RunUserBalanceRecord record) throws AppException;

	/**
	 * 更新用户余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	void updateRunUserBalanceRecord(RunUserBalanceRecord record) throws AppException;

	/**
	 * 根据用户id删除用户余额记录
	 *
	 * @param id
	 * @throws AppException
	 */
	void deleteRunUserBalanceRecordByID(Integer id) throws AppException;

	/**
	 * 根据用户ID删除所有用户余额记录
	 *
	 * @param uid
	 * @throws AppException
	 */
	void deleteAllRunUserBalanceRecordByUID(Integer uid) throws AppException;

	/**
	 * 根据id获取余额记录
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	RunUserBalanceRecord queryRunUserBalanceRecordByID(Integer id) throws AppException;

	/**
	 * 根据用户id和流水号查询交易记录
	 *
	 * @param uid
	 * @param number
	 * @return
	 * @throws AppException
	 */
	RunUserBalanceRecord queryRunUserBalanceRecordByUID(Integer uid, String number) throws AppException;

	/**
	 * 根据用户id分页获取余额记录
	 *
	 * @param uid
	 * @param page
	 * @param size
	 * @param orderField
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	PageInfo<RunUserBalanceRecord> pageAllRunUserBalanceRecordByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException;
}
