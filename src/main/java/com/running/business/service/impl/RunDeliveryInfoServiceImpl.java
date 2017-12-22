package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryInfoMapper;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.service.RunDeliveryInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RunDeliveryInfoServiceImpl implements RunDeliveryInfoService{

	@Autowired
	private RunDeliveryInfoMapper runDeliveryInfoMapper;

	@Override
	public BaseResult addRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
		runDeliveryInfoMapper.insert(deliveryInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunDeliveryInfo(RunDeliveryInfo deliveryInfo) {
		runDeliveryInfoMapper.updateByPrimaryKey(deliveryInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult delRunDeliveryInfoByID(Integer did) {
		RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
		if (info == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryInfoMapper.deleteByPrimaryKey(did);
		return BaseResult.success(info);
	}

	@Override
	public BaseResult getRunDeliveryInfoByID(Integer did) {
		RunDeliveryInfo info = runDeliveryInfoMapper.selectByPrimaryKey(did);
		if (info == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(info);
	}
}
