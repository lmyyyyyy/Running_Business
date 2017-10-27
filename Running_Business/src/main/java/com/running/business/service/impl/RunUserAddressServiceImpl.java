package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserAddressMapper;
import com.running.business.pojo.RunUserAddress;
import com.running.business.pojo.RunUserAddressExample;
import com.running.business.pojo.RunUserAddressExample.Criteria;
import com.running.business.service.RunUserAddressService;
import com.running.business.util.ValidateUtil;

@Service
public class RunUserAddressServiceImpl implements RunUserAddressService{

	@Autowired
	private RunUserAddressMapper runUserAddressMapper;
	
	@Override
	public BaseResult<Object> addRunUserAddress(RunUserAddress userAddress) {
		int id = runUserAddressMapper.insert(userAddress);
		System.out.println(id);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunUserAddress(RunUserAddress userAddress) {
		int id = runUserAddressMapper.updateByPrimaryKey(userAddress);
		System.out.println(id);
		return BaseResult.success(userAddress);
	}

	@Override
	public BaseResult<Object> delRunUserAddressByID(Integer id) {
		RunUserAddress userAddress = runUserAddressMapper.selectByPrimaryKey(id);
		if (userAddress == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runUserAddressMapper.deleteByPrimaryKey(id);
		return BaseResult.success(userAddress);
	}

	@Override
	public BaseResult<Object> getRunUserAddressByID(Integer id) {
		RunUserAddress userAddress = runUserAddressMapper.selectByPrimaryKey(id);
		if (userAddress == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(userAddress);
	}

	@Override
	public BaseResult<Object> getAllRunUserAddress(Integer uid) {
		RunUserAddressExample example = new RunUserAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserAddress> list = runUserAddressMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> delAllRunUserAddressByUID(Integer uid) {
		RunUserAddressExample example = new RunUserAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserAddress> list = runUserAddressMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunUserAddressExample example1 = new RunUserAddressExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runUserAddressMapper.deleteByExample(example1);
		return BaseResult.success();
	}

}
