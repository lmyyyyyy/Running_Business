package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunInfoMapper;
import com.running.business.pojo.RunInfo;
import com.running.business.pojo.RunInfoExample;
import com.running.business.service.RunInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunInfoServiceImpl implements RunInfoService{

	@Autowired
	private RunInfoMapper runInfoMapper;

	@Override
	public BaseResult saveRunInfo(RunInfo runInfo) {
		runInfoMapper.insert(runInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunInfo(RunInfo runInfo) {
		runInfoMapper.updateByPrimaryKey(runInfo);
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult deleteRunInfoByID(Integer id) {
		RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
		if (runInfo == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runInfoMapper.deleteByPrimaryKey(id);
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult deleteAllRunInfo() {
		RunInfoExample example = new RunInfoExample();
		runInfoMapper.deleteByExample(example);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunInfoByID(Integer id) {
		RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
		if (runInfo == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult getAllRunInfo() {
		RunInfoExample example = new RunInfoExample();
		List<RunInfo> list = runInfoMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllRunInfoByDate(long seconds) {
		//TODO
		return null;
	}
}
