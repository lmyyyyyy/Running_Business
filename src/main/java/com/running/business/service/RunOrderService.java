package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunOrder;

public interface RunOrderService {

	BaseResult saveRunOrder(RunOrder order);
	BaseResult updateRunOrder(RunOrder order);
	BaseResult deleteRunOrderByOID(String oid);
	BaseResult deleteAllRunOrderByUID(Integer uid);
	BaseResult deleteAllRunOrderByDID(Integer did);

	BaseResult getRunOrderByOID(String oid);
	BaseResult getAllRunOrderByUID(Integer uid);
	BaseResult getAllRunOrderByUID(Integer uid, Integer currentPage);
	BaseResult getAllRunOrderByDID(Integer did);
	BaseResult getAllRunOrderByDID(Integer did, Integer currentPage);
	BaseResult getAllRunOrder();

	BaseResult getOrderByUIDAndStatus(int id, int status, int currentPage);
}
