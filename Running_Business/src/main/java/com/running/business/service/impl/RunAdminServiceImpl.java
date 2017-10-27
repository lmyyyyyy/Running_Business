package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunAdminMapper;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminExample;
import com.running.business.service.RunAdminService;

@Service
public class RunAdminServiceImpl implements RunAdminService{
	
	@Autowired
	private RunAdminMapper runAdminMapper;

	@Override
	public BaseResult<Object> addRunAdmin(RunAdmin admin) {
		runAdminMapper.insert(admin);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunAdmin(RunAdmin admin) {
		runAdminMapper.updateByPrimaryKey(admin);
		return BaseResult.success(admin);
	}

	@Override
	public BaseResult<Object> delRunAdminByID(Integer id) {
		RunAdmin admin = runAdminMapper.selectByPrimaryKey(id);
		if (admin == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runAdminMapper.deleteByPrimaryKey(id);
		return BaseResult.success(admin);
	}

	@Override
	public BaseResult<Object> getRunAdminByID(Integer id) {
		RunAdmin admin = runAdminMapper.selectByPrimaryKey(id);
		if (admin == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(admin);
	}

	@Override
	public BaseResult<Object> getAllRunAdmin() {
		RunAdminExample example = new RunAdminExample();
		List<RunAdmin> list = runAdminMapper.selectByExample(example);
		if (list == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
