package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderPay;

public interface RunOrderPayService {

	/**
	 * 检查该订单是否支付过true:支付过；false:未支付过
	 *
	 * @param uid
	 * @param orderId
	 * @return
	 * @throws AppException
	 */
	boolean checkIsPay(Integer uid, String orderId) throws AppException;

	/**
	 * 保存支付记录
	 *
	 * @param pay
	 * @throws AppException
	 */
	void saveRunOrderPay(RunOrderPay pay) throws AppException;

	void updateRunOrderPay(RunOrderPay pay) throws AppException;
	void deleteRunOrderPayByID(Integer id) throws AppException;
	void deleteRunOrderPayByOID(String oid) throws AppException;
	void deleteAllRunOrderByUID(Integer uid) throws AppException;
	
	BaseResult getRunOrderPayByID(Integer id) throws AppException;
	BaseResult getRunOrderPayByOID(String oid) throws AppException;
	BaseResult getAllRunOrderPayByUID(Integer uid) throws AppException;
	BaseResult getAllRunOrderPay() throws AppException;

	/**
	 * 根据订单号和用户id获取支付信息
	 *
	 * @param orderId
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	RunOrderPay queryPayByOIDAndUID(String orderId, Integer uid) throws AppException;

	/**
	 * 根据用户id获取支付总数
	 *
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	Integer payCountByUIDOrDId(Integer uid) throws AppException;

	/**
	 * 根据用户id和支付类型分页查询支付记录
	 *
	 * @param uid
	 * @param type
	 * @param page
	 * @param size
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	PageInfo<RunOrderPay> pagePaysByUIDAndType(Integer uid, Integer type, Integer page, Integer size, String orderType) throws AppException;
}
