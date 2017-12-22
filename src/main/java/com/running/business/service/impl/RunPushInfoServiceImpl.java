package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunPushInfoMapper;
import com.running.business.pojo.RunPushInfo;
import com.running.business.pojo.RunPushInfoExample;
import com.running.business.service.RunPushInfoService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunPushInfoServiceImpl implements RunPushInfoService{

	@Autowired
	private RunPushInfoMapper runPushInfoMapper;

	@Override
	public BaseResult addRunPushInfo(RunPushInfo pushInfo) {
		runPushInfoMapper.insert(pushInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult updatePushInfo(RunPushInfo pushInfo) {
		runPushInfoMapper.updateByPrimaryKey(pushInfo);
		return BaseResult.success(pushInfo);
	}

	@Override
	public BaseResult delPushInfoByID(Integer id) {
		RunPushInfo pushInfo = runPushInfoMapper.selectByPrimaryKey(id);
		if (pushInfo == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runPushInfoMapper.deleteByPrimaryKey(id);
		return BaseResult.success(pushInfo);
	}

	@Override
	public BaseResult getRunPushInfoByID(Integer id) {
		RunPushInfo pushInfo = runPushInfoMapper.selectByPrimaryKey(id);
		if (pushInfo == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(pushInfo);
	}

	@Override
	public BaseResult getAllRunPushInfo() {
		RunPushInfoExample example = new RunPushInfoExample();
		List<RunPushInfo> list = runPushInfoMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
}
