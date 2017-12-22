package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderPay;

public interface RunOrderPayService {
	
	BaseResult addRunOrderPay(RunOrderPay pay);
	BaseResult updateRunOrderPay(RunOrderPay pay);
	BaseResult delRunOrderPayByID(Integer id);
	BaseResult delRunOrderPayByOID(String oid);
	BaseResult delAllRunOrderByUID(Integer uid);
	
	BaseResult getRunOrderPayByID(Integer id);
	BaseResult getRunOrderPayByOID(String oid);
	BaseResult getAllRunOrderPayByUID(Integer uid);
	BaseResult getAllRunOrderPay();
}
