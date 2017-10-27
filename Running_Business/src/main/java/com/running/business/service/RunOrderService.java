package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrder;

public interface RunOrderService {

	BaseResult<Object> addRunOrder(RunOrder order);
	BaseResult<Object> updateRunOrder(RunOrder order);
	BaseResult<Object> delRunOrderByOID(String oid);
	BaseResult<Object> delAllRunOrderByUID(Integer uid);
	BaseResult<Object> delAllRunOrderByDID(Integer did);

	BaseResult<Object> getRunOrderByOID(String oid);
	BaseResult<Object> getAllRunOrderByUID(Integer uid);
	BaseResult<Object> getAllRunOrderByDID(Integer did);
	BaseResult<Object> getAllRunOrder();
}
