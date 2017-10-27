package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunOrderStatusMapper;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderStatus;
import com.running.business.pojo.RunOrderStatusExample;
import com.running.business.pojo.RunOrderStatusExample.Criteria;
import com.running.business.service.RunOrderStatusService;
import com.running.business.util.ValidateUtil;

@Service
public class RunOrderStatusServiceImpl implements RunOrderStatusService{

	@Autowired
	private RunOrderStatusMapper runOrderStatusMapper;

	@Override
	public BaseResult<Object> addRunOrderStatus(RunOrderStatus order) {
		runOrderStatusMapper.insert(order);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunOrderStatus(RunOrderStatus order) {
		runOrderStatusMapper.updateByPrimaryKey(order);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult<Object> delRunOrderStatusByOID(String oid) {
		RunOrderStatus order = runOrderStatusMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runOrderStatusMapper.deleteByPrimaryKey(oid);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult<Object> delAllRunOrderStatusByUID(Integer uid) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunOrderStatusExample example1 = new RunOrderStatusExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runOrderStatusMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delAllRunOrderStatusByDID(Integer did) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunOrderStatusExample example1 = new RunOrderStatusExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runOrderStatusMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunOrderStatusByOID(String oid) {
		RunOrderStatus order = runOrderStatusMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(order);
	}

	@Override
	public BaseResult<Object> getAllRunOrderStatusByUID(Integer uid) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> getAllRunOrderStatusByDID(Integer did) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> getAllRunOrderStatus() {
		RunOrderStatusExample example = new RunOrderStatusExample();
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
