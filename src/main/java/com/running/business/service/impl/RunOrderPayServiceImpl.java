package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunOrderPayMapper;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunOrderPayExample;
import com.running.business.pojo.RunOrderPayExample.Criteria;
import com.running.business.service.RunOrderPayService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunOrderPayServiceImpl implements RunOrderPayService{
	
	@Autowired
	private RunOrderPayMapper runOrderPayMapper;

	@Override
	public BaseResult saveRunOrderPay(RunOrderPay pay) {
		runOrderPayMapper.insert(pay);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunOrderPay(RunOrderPay pay) {
		runOrderPayMapper.updateByPrimaryKey(pay);
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult deleteRunOrderPayByID(Integer id) {
		RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
		if (pay == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runOrderPayMapper.deleteByPrimaryKey(id);
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult deleteRunOrderPayByOID(String oid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(oid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderPayExample example1 = new RunOrderPayExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andOrderidEqualTo(oid);
		runOrderPayMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult deleteAllRunOrderByUID(Integer uid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderPayExample example1 = new RunOrderPayExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runOrderPayMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunOrderPayByID(Integer id) {
		RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
		if (pay == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult getRunOrderPayByOID(String oid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(oid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllRunOrderPayByUID(Integer uid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllRunOrderPay() {
		RunOrderPayExample example = new RunOrderPayExample();
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
}
