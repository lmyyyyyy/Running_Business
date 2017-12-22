package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunOrderStatusMapper;
import com.running.business.pojo.RunOrderStatus;
import com.running.business.pojo.RunOrderStatusExample;
import com.running.business.pojo.RunOrderStatusExample.Criteria;
import com.running.business.service.RunOrderStatusService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunOrderStatusServiceImpl implements RunOrderStatusService{

	@Autowired
	private RunOrderStatusMapper runOrderStatusMapper;

	@Override
	public BaseResult addRunOrderStatus(RunOrderStatus order) {
		runOrderStatusMapper.insert(order);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunOrderStatus(RunOrderStatus order) {
		runOrderStatusMapper.updateByPrimaryKey(order);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult delRunOrderStatusByOID(String oid) {
		RunOrderStatus order = runOrderStatusMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runOrderStatusMapper.deleteByPrimaryKey(oid);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult delAllRunOrderStatusByUID(Integer uid) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderStatusExample example1 = new RunOrderStatusExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runOrderStatusMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult delAllRunOrderStatusByDID(Integer did) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderStatusExample example1 = new RunOrderStatusExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runOrderStatusMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunOrderStatusByOID(String oid) {
		RunOrderStatus order = runOrderStatusMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(order);
	}

	@Override
	public BaseResult getAllRunOrderStatusByUID(Integer uid) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllRunOrderStatusByDID(Integer did) {
		RunOrderStatusExample example = new RunOrderStatusExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllRunOrderStatus() {
		RunOrderStatusExample example = new RunOrderStatusExample();
		List<RunOrderStatus> list = runOrderStatusMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
}
