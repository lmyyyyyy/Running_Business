package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.mapper.RunUserMapper;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserExample;
import com.running.business.pojo.RunUserExample.Criteria;
import com.running.business.service.RunUserService;
import com.running.business.util.ValidateUtil;

@Service
public class RunUserServiceImpl implements RunUserService{

	@Autowired
	private RunUserMapper runUserMapper;
	
	@Override
	public int addUser(RunUser user) {
		return runUserMapper.insert(user);
	}

	@Override
	public void delUser(Integer uid) {
		runUserMapper.deleteByPrimaryKey(uid);
	}

	@Override
	public void updateUser(RunUser user) {
		runUserMapper.updateByPrimaryKey(user);
	}

	@Override
	public RunUser getRunUser(Integer id) {
		return runUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public RunUser getRunUser(String username, String password) {
		RunUserExample example = new RunUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserUsernameEqualTo(username);
		criteria.andUserPasswordEqualTo(password);
		List<RunUser> list = runUserMapper.selectByExample(example);
		return ValidateUtil.isValid(list) ? list.get(0) : null;
	}

	@Override
	public List<RunUser> getAllRunUser() {
		RunUserExample example = new RunUserExample();
		List<RunUser> list = runUserMapper.selectByExample(example);
		return ValidateUtil.isValid(list) ? list : null;
	}

}
