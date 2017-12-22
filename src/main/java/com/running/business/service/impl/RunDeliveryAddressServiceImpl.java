package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryAddressMapper;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryAddressExample;
import com.running.business.pojo.RunDeliveryAddressExample.Criteria;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunDeliveryAddressServiceImpl implements RunDeliveryAddressService{

	@Autowired
	private RunDeliveryAddressMapper runDeliveryAddressMapper;

	@Override
	public BaseResult addRunDeliveryAddress(RunDeliveryAddress address) {
		runDeliveryAddressMapper.insert(address);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunDeliveryAddress(
			RunDeliveryAddress address) {
		runDeliveryAddressMapper.updateByPrimaryKey(address);
		return BaseResult.success(address);
	}

	@Override
	public BaseResult delRunDeliveryAddressByID(Integer id) {
		RunDeliveryAddress address = runDeliveryAddressMapper.selectByPrimaryKey(id);
		if (address == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryAddressMapper.deleteByPrimaryKey(id);
		return BaseResult.success(address);
	}

	@Override
	public BaseResult delAllRunDeliveryAddressByDID(Integer did) {
		RunDeliveryAddressExample example = new RunDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryAddress> list = runDeliveryAddressMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunDeliveryAddressExample example1 = new RunDeliveryAddressExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runDeliveryAddressMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunDeliveryAddressByID(Integer id) {
		RunDeliveryAddress address = runDeliveryAddressMapper.selectByPrimaryKey(id);
		if (address == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(address);
	}

	@Override
	public BaseResult getAllRunDeliveryAddressByDID(Integer did) {
		RunDeliveryAddressExample example = new RunDeliveryAddressExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryAddress> list = runDeliveryAddressMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
	
}
