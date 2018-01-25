package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderStatus;

public interface RunOrderStatusService {

	BaseResult saveRunOrderStatus(RunOrderStatus order);
	BaseResult updateRunOrderStatus(RunOrderStatus order);
	BaseResult deleteRunOrderStatusByOID(String oid);
	BaseResult deleteAllRunOrderStatusByUID(Integer uid);
	BaseResult deleteAllRunOrderStatusByDID(Integer did);

	BaseResult getRunOrderStatusByOID(String oid);
	BaseResult getAllRunOrderStatusByUID(Integer uid);
	BaseResult getAllRunOrderStatusByDID(Integer did);
	BaseResult getAllRunOrderStatus();
	
}
