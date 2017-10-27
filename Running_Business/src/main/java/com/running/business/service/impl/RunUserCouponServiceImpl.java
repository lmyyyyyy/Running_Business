package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserCouponMapper;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.pojo.RunUserCouponExample;
import com.running.business.pojo.RunUserCouponExample.Criteria;
import com.running.business.service.RunUserCouponService;

@Service
public class RunUserCouponServiceImpl implements RunUserCouponService{

	@Autowired
	private RunUserCouponMapper runUserCouponMapper;

	@Override
	public BaseResult<Object> addRunUserCoupon(RunUserCoupon runUserCoupon) {
		runUserCouponMapper.insert(runUserCoupon);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunUserCoupon(RunUserCoupon runUserCoupon) {
		runUserCouponMapper.updateByPrimaryKey(runUserCoupon);
		return BaseResult.success(runUserCoupon);
	}

	@Override
	public BaseResult<Object> delRunUserCouponByID(Integer id) {
		RunUserCoupon userCoupon = runUserCouponMapper.selectByPrimaryKey(id);
		if (userCoupon == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runUserCouponMapper.deleteByPrimaryKey(id);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delAllRunUserCoupon(Integer uid) {
		RunUserCouponExample example = new RunUserCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		runUserCouponMapper.deleteByExample(example);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunUserCouponByID(Integer id) {
		RunUserCoupon userCoupon = runUserCouponMapper.selectByPrimaryKey(id);
		if (userCoupon == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(userCoupon);
	}

	@Override
	public BaseResult<Object> getAllRunUserCouponByUID(Integer uid) {
		RunUserCouponExample example = new RunUserCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserCoupon> list = runUserCouponMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
