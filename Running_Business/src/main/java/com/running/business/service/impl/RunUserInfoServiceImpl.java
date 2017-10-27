package com.running.business.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.pojo.RunUserInfo;
import com.running.business.pojo.RunUserInfoExample;
import com.running.business.service.RunUserInfoService;

@Service
public class RunUserInfoServiceImpl implements RunUserInfoService{

	@Autowired
	private RunUserInfoMapper runUserInfoMapper;
	
	@Override
	public BaseResult<Object> addRunUserInfo(RunUserInfo userInfo) {
		runUserInfoMapper.insert(userInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunUserInfo(RunUserInfo userInfo) {
		runUserInfoMapper.updateByPrimaryKey(userInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delRunUserInfo(Integer uid) {
		runUserInfoMapper.deleteByPrimaryKey(uid);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunUserInfoById(Integer uid) {
		RunUserInfo userInfo = runUserInfoMapper.selectByPrimaryKey(uid);
		if (userInfo == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(userInfo);
	}

	@Override
	public BaseResult<Object> getAllRunUserInfo() {
		RunUserInfoExample example = new RunUserInfoExample();
		return BaseResult.success(runUserInfoMapper.selectByExample(example));
	}
}
