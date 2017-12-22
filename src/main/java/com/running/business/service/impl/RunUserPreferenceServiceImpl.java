package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserPreferenceMapper;
import com.running.business.pojo.RunUserPreference;
import com.running.business.pojo.RunUserPreferenceExample;
import com.running.business.pojo.RunUserPreferenceExample.Criteria;
import com.running.business.service.RunUserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunUserPreferenceServiceImpl implements RunUserPreferenceService{
	
	@Autowired
	private RunUserPreferenceMapper runUserPreferenceMapper;

	@Override
	public BaseResult addRunUserPreference(RunUserPreference userpre) {
		runUserPreferenceMapper.insert(userpre);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunUserPreference(RunUserPreference userpre) {
		runUserPreferenceMapper.updateByPrimaryKey(userpre);
		return BaseResult.success(userpre);
	}

	@Override
	public BaseResult delRunUserPreferenceByID(Integer id) {
		RunUserPreference userpre = runUserPreferenceMapper.selectByPrimaryKey(id);
		if (userpre == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runUserPreferenceMapper.deleteByPrimaryKey(id);
		return BaseResult.success();
	}

	@Override
	public BaseResult delAllRunUserPreferenceByUID(Integer uid) {
		RunUserPreferenceExample example = new RunUserPreferenceExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		runUserPreferenceMapper.deleteByExample(example);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunUserPreferenceByID(Integer id) {
		RunUserPreference userpre = runUserPreferenceMapper.selectByPrimaryKey(id);
		if (userpre == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(),ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(userpre);
	}

	@Override
	public BaseResult getAllUserPreferenceByUID(Integer uid) {
		RunUserPreferenceExample example = new RunUserPreferenceExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserPreference> list = runUserPreferenceMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	@Override
	public BaseResult getAllUserPreferenceByUIDAndType(Integer uid,
			String type) {
		RunUserPreferenceExample example = new RunUserPreferenceExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		criteria.andUserGoodstypeEqualTo(type);
		List<RunUserPreference> list = runUserPreferenceMapper.selectByExample(example);
		if (list == null || list.size() == 0) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

}
