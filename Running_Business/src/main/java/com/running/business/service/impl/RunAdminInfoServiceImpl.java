package com.running.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunAdminInfoMapper;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.service.RunAdminInfoService;

@Service
public class RunAdminInfoServiceImpl implements RunAdminInfoService{

	@Autowired
	private RunAdminInfoMapper runAdminInfoMapper;

	@Override
	public BaseResult<Object> addRunAdminInfo(RunAdminInfo adminInfo) {
		runAdminInfoMapper.insert(adminInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunAdminInfo(RunAdminInfo adminInfo) {
		runAdminInfoMapper.updateByPrimaryKey(adminInfo);
		return BaseResult.success(adminInfo);
	}

	@Override
	public BaseResult<Object> delRunAdminInfoByID(Integer id) {
		RunAdminInfo adminInfo = runAdminInfoMapper.selectByPrimaryKey(id);
		if (adminInfo == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runAdminInfoMapper.deleteByPrimaryKey(id);
		return BaseResult.success(adminInfo);
	}

	@Override
	public BaseResult<Object> getRunAdminInfoByID(Integer id) {
		RunAdminInfo adminInfo = runAdminInfoMapper.selectByPrimaryKey(id);
		if (adminInfo == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(adminInfo);
	}
}
