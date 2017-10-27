package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserAddress;

public interface RunUserAddressService {

	BaseResult<Object> addRunUserAddress(RunUserAddress userAddress);
	BaseResult<Object> updateRunUserAddress(RunUserAddress userAddress);
	BaseResult<Object> delRunUserAddressByID(Integer id);
	BaseResult<Object> delAllRunUserAddressByUID(Integer uid);
	
	BaseResult<Object> getRunUserAddressByID(Integer id);
	BaseResult<Object> getAllRunUserAddress(Integer uid);
}
