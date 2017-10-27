package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunInfoMapper;
import com.running.business.pojo.RunInfo;
import com.running.business.pojo.RunInfoExample;
import com.running.business.service.RunInfoService;

@Service
public class RunInfoServiceImpl implements RunInfoService{

	@Autowired
	private RunInfoMapper runInfoMapper;

	@Override
	public BaseResult<Object> addRunInfo(RunInfo runInfo) {
		runInfoMapper.insert(runInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunInfo(RunInfo runInfo) {
		runInfoMapper.updateByPrimaryKey(runInfo);
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult<Object> delRunInfoByID(Integer id) {
		RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
		if (runInfo == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runInfoMapper.deleteByPrimaryKey(id);
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult<Object> delAllRunInfo() {
		RunInfoExample example = new RunInfoExample();
		runInfoMapper.deleteByExample(example);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunInfoByID(Integer id) {
		RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
		if (runInfo == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(runInfo);
	}

	@Override
	public BaseResult<Object> getAllRunInfo() {
		RunInfoExample example = new RunInfoExample();
		List<RunInfo> list = runInfoMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult<Object> getAllRunInfoByDate(long seconds) {
		//TODO
		return null;
	}
}
