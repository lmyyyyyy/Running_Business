package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderPay;

public interface RunOrderPayService {
	
	BaseResult<Object> addRunOrderPay(RunOrderPay pay);
	BaseResult<Object> updateRunOrderPay(RunOrderPay pay);
	BaseResult<Object> delRunOrderPayByID(Integer id);
	BaseResult<Object> delRunOrderPayByOID(String oid);
	BaseResult<Object> delAllRunOrderByUID(Integer uid);
	
	BaseResult<Object> getRunOrderPayByID(Integer id);
	BaseResult<Object> getRunOrderPayByOID(String oid);
	BaseResult<Object> getAllRunOrderPayByUID(Integer uid);
	BaseResult<Object> getAllRunOrderPay();
}
