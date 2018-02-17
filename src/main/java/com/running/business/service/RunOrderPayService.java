package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrderPay;

public interface RunOrderPayService {
	
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
}
