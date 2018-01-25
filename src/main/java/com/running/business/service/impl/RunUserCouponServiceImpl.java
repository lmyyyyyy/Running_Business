package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserCouponMapper;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.pojo.RunUserCouponExample;
import com.running.business.pojo.RunUserCouponExample.Criteria;
import com.running.business.service.RunUserCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunUserCouponServiceImpl implements RunUserCouponService{

	@Autowired
	private RunUserCouponMapper runUserCouponMapper;

	@Override
	public BaseResult saveRunUserCoupon(RunUserCoupon runUserCoupon) {
		runUserCouponMapper.insert(runUserCoupon);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunUserCoupon(RunUserCoupon runUserCoupon) {
		runUserCouponMapper.updateByPrimaryKey(runUserCoupon);
		return BaseResult.success(runUserCoupon);
	}

	@Override
	public BaseResult deleteRunUserCouponByID(Integer id) {
		RunUserCoupon userCoupon = runUserCouponMapper.selectByPrimaryKey(id);
		if (userCoupon == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runUserCouponMapper.deleteByPrimaryKey(id);
		return BaseResult.success();
	}

	@Override
	public BaseResult deleteAllRunUserCoupon(Integer uid) {
		RunUserCouponExample example = new RunUserCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		runUserCouponMapper.deleteByExample(example);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunUserCouponByID(Integer id) {
		RunUserCoupon userCoupon = runUserCouponMapper.selectByPrimaryKey(id);
		if (userCoupon == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(userCoupon);
	}

	@Override
	public BaseResult getAllRunUserCouponByUID(Integer uid) {
		RunUserCouponExample example = new RunUserCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserCoupon> list = runUserCouponMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
}
