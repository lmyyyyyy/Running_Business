package com.running.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryInfoMapper;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.service.RunDeliveryInfoService;

@Service
public class RunDeliveryInfoServiceImpl implements RunDeliveryInfoService{

	@Autowired
	private RunDeliveryInfoMapper runDeliveryInfoMapper;

	@Override
	public BaseResult<Object> addRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
		runDeliveryInfoMapper.insert(deliveryInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
		runDeliveryInfoMapper.updateByPrimaryKey(deliveryInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delRunDeliveryInfoByID(Integer did) {
		RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
		if (info == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runDeliveryInfoMapper.deleteByPrimaryKey(did);
		return BaseResult.success(info);
	}

	@Override
	public BaseResult<Object> getRunDeliveryInfoByID(Integer did) {
		RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
		if (info == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(info);
	}
}
