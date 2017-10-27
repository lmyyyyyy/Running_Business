package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserCoupon;

public interface RunUserCouponService {

	BaseResult<Object> addRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult<Object> updateRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult<Object> delRunUserCouponByID(Integer id);
	BaseResult<Object> delAllRunUserCoupon(Integer uid);
	
	BaseResult<Object> getRunUserCouponByID(Integer id);
	BaseResult<Object> getAllRunUserCouponByUID(Integer uid);
}
