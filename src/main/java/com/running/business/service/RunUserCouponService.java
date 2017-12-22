package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.pojo.RunUserCoupon;

public interface RunUserCouponService {

	BaseResult addRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult updateRunUserCoupon(RunUserCoupon runUserCoupon);
	BaseResult delRunUserCouponByID(Integer id);
	BaseResult delAllRunUserCoupon(Integer uid);
	
	BaseResult getRunUserCouponByID(Integer id);
	BaseResult getAllRunUserCouponByUID(Integer uid);
}
