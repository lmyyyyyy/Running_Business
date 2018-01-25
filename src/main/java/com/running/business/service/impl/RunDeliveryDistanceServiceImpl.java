package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryDistanceMapper;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.service.RunDeliveryDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunDeliveryDistanceServiceImpl implements RunDeliveryDistanceService{

	@Autowired
	private RunDeliveryDistanceMapper runDeliveryDistanceMapper;

	@Override
	public BaseResult saveRunDeliveryDistance(
			RunDeliveryDistance distance) {
		runDeliveryDistanceMapper.insert(distance);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunDeliveryDistance(
			RunDeliveryDistance distance) {
		runDeliveryDistanceMapper.updateByPrimaryKey(distance);
		return BaseResult.success(distance);
	}

	@Override
	public BaseResult deleteRunDeliveryDistanceByDID(Integer did) {
		RunDeliveryDistance distance = runDeliveryDistanceMapper.selectByPrimaryKey(did);
		if (distance == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryDistanceMapper.deleteByPrimaryKey(did);
		return BaseResult.success(distance);
	}

	@Override
	public BaseResult getRunDeliveryDistanceByDID(Integer did) {
		RunDeliveryDistance distance = runDeliveryDistanceMapper.selectByPrimaryKey(did);
		if (distance == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(distance);
	}
}
