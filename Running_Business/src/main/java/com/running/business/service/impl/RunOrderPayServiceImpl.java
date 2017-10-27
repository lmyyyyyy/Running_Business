package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunOrderPayMapper;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunOrderPayExample;
import com.running.business.pojo.RunOrderPayExample.Criteria;
import com.running.business.service.RunOrderPayService;
import com.running.business.util.ValidateUtil;

@Service
public class RunOrderPayServiceImpl implements RunOrderPayService{
	
	@Autowired
	private RunOrderPayMapper runOrderPayMapper;

	@Override
	public BaseResult<Object> addRunOrderPay(RunOrderPay pay) {
		runOrderPayMapper.insert(pay);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunOrderPay(RunOrderPay pay) {
		runOrderPayMapper.updateByPrimaryKey(pay);
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult<Object> delRunOrderPayByID(Integer id) {
		RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
		if (pay == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runOrderPayMapper.deleteByPrimaryKey(id);
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult<Object> delRunOrderPayByOID(String oid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(oid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunOrderPayExample example1 = new RunOrderPayExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andOrderidEqualTo(oid);
		runOrderPayMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delAllRunOrderByUID(Integer uid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunOrderPayExample example1 = new RunOrderPayExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runOrderPayMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunOrderPayByID(Integer id) {
		RunOrderPay pay = runOrderPayMapper.selectByPrimaryKey(id);
		if (pay == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(pay);
	}

	@Override
	public BaseResult<Object> getRunOrderPayByOID(String oid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andOrderidEqualTo(oid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> getAllRunOrderPayByUID(Integer uid) {
		RunOrderPayExample example = new RunOrderPayExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> getAllRunOrderPay() {
		RunOrderPayExample example = new RunOrderPayExample();
		List<RunOrderPay> list = runOrderPayMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
