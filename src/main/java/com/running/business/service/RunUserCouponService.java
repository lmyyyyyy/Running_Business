package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserCoupon;

public interface RunUserCouponService {

	BaseResult saveRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult updateRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult deleteRunUserCouponByID(Integer id);
	BaseResult deleteAllRunUserCoupon(Integer uid);
	
	BaseResult getRunUserCouponByID(Integer id);
	BaseResult getAllRunUserCouponByUID(Integer uid);
}
