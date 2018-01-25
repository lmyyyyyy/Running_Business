package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderPay;

public interface RunOrderPayService {
	
	BaseResult saveRunOrderPay(RunOrderPay pay);
	BaseResult updateRunOrderPay(RunOrderPay pay);
	BaseResult deleteRunOrderPayByID(Integer id);
	BaseResult deleteRunOrderPayByOID(String oid);
	BaseResult deleteAllRunOrderByUID(Integer uid);
	
	BaseResult getRunOrderPayByID(Integer id);
	BaseResult getRunOrderPayByOID(String oid);
	BaseResult getAllRunOrderPayByUID(Integer uid);
	BaseResult getAllRunOrderPay();
}
