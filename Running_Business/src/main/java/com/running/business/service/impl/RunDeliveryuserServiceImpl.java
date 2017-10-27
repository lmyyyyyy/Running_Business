package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryuserMapper;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunDeliveryuserExample;
import com.running.business.pojo.RunDeliveryuserExample.Criteria;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.util.Run_StringUtil;
import com.running.business.util.ValidateUtil;

@Service
public class RunDeliveryuserServiceImpl implements RunDeliveryuserService{
	
	@Autowired
	private RunDeliveryuserMapper runDeliveryuserMapper;

	@Override
	public BaseResult<Object> addRunDeliveryuser(RunDeliveryuser user) {
		user.setDeliveryPassword(Run_StringUtil.MD5(user.getDeliveryPassword()));
		runDeliveryuserMapper.insert(user);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> delRunDeliveryuser(Integer uid) {
		RunDeliveryuser user = runDeliveryuserMapper.selectByPrimaryKey(uid);
		if (user == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runDeliveryuserMapper.deleteByPrimaryKey(uid);
		return BaseResult.success(user);
	}

	@Override
	public BaseResult<Object> updateRunDeliveryuser(RunDeliveryuser user) {
		user.setDeliveryPassword(Run_StringUtil.MD5(user.getDeliveryPassword()));
		runDeliveryuserMapper.updateByPrimaryKey(user);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> checkRunDeliveryuser(String username) {
		RunDeliveryuserExample example = new RunDeliveryuserExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeliveryUsernameEqualTo(username);
		List<RunDeliveryuser> list = runDeliveryuserMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.success();
		}
		return BaseResult.fail(new ErrorMsg(ResultEnum.TELTPHONE_USED.getCode(), ResultEnum.TELTPHONE_USED.getMsg()));
	}

	@Override
	public BaseResult<Object> getRunDeliveryuser(Integer id) {
		RunDeliveryuser user = runDeliveryuserMapper.selectByPrimaryKey(id);
		if (user == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(user);
	}

	@Override
	public BaseResult<Object> login(String username, String password) {
		RunDeliveryuserExample example = new RunDeliveryuserExample();
		Criteria criteria = example.createCriteria();
		criteria.andDeliveryUsernameEqualTo(username);
		List<RunDeliveryuser> list = null;
		list = runDeliveryuserMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.TELTPHONE_NOT_REG.getCode(), ResultEnum.TELTPHONE_NOT_REG.getMsg()));
		}
		RunDeliveryuserExample example1 = new RunDeliveryuserExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDeliveryUsernameEqualTo(username);
		criteria1.andDeliveryPasswordEqualTo(Run_StringUtil.MD5(password));
		list = runDeliveryuserMapper.selectByExample(example1);
		return ValidateUtil.isValid(list) ? BaseResult.success(list.get(0)) : BaseResult.fail(new ErrorMsg(ResultEnum.PWD_ERROR.getCode(), ResultEnum.PWD_ERROR.getMsg()));
	}

	@Override
	public BaseResult<Object> getAllRunDeliveryuser() {
		RunDeliveryuserExample example = null;
		List<RunDeliveryuser> list = null;
		example = new RunDeliveryuserExample();
		example.setOrderByClause("delivery_date DESC");
		list = runDeliveryuserMapper.selectByExample(example);
		return ValidateUtil.isValid(list) ? BaseResult.success(list) : BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
	}
}
