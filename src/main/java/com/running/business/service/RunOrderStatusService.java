package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrderStatus;

public interface RunOrderStatusService {

	BaseResult addRunOrderStatus(RunOrderStatus order);
	BaseResult updateRunOrderStatus(RunOrderStatus order);
	BaseResult delRunOrderStatusByOID(String oid);
	BaseResult delAllRunOrderStatusByUID(Integer uid);
	BaseResult delAllRunOrderStatusByDID(Integer did);

	BaseResult getRunOrderStatusByOID(String oid);
	BaseResult getAllRunOrderStatusByUID(Integer uid);
	BaseResult getAllRunOrderStatusByDID(Integer did);
	BaseResult getAllRunOrderStatus();
	
}
