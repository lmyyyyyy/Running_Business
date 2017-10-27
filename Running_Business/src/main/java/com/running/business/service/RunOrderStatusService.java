package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderStatus;

public interface RunOrderStatusService {

	BaseResult<Object> addRunOrderStatus(RunOrderStatus order);
	BaseResult<Object> updateRunOrderStatus(RunOrderStatus order);
	BaseResult<Object> delRunOrderStatusByOID(String oid);
	BaseResult<Object> delAllRunOrderStatusByUID(Integer uid);
	BaseResult<Object> delAllRunOrderStatusByDID(Integer did);

	BaseResult<Object> getRunOrderStatusByOID(String oid);
	BaseResult<Object> getAllRunOrderStatusByUID(Integer uid);
	BaseResult<Object> getAllRunOrderStatusByDID(Integer did);
	BaseResult<Object> getAllRunOrderStatus();
	
}
