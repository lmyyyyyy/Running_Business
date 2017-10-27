package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunUserBalanceRecordMapper;
import com.running.business.pojo.RunUserBalanceRecord;
import com.running.business.pojo.RunUserBalanceRecordExample;
import com.running.business.pojo.RunUserBalanceRecordExample.Criteria;
import com.running.business.service.RunUserBalanceRecordService;
import com.running.business.util.ValidateUtil;

@Service
public class RunUserBalanceRecordServiceImpl implements RunUserBalanceRecordService{

	@Autowired
	private RunUserBalanceRecordMapper runUserBalanceRecordMapper;

	@Override
	public BaseResult<Object> addRunUserBalanceRecord(
			RunUserBalanceRecord record) {
		runUserBalanceRecordMapper.insert(record);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunUserBalanceRecord(
			RunUserBalanceRecord record) {
		runUserBalanceRecordMapper.updateByPrimaryKey(record);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> delRunUserBalanceRecordByID(Integer id) {
		RunUserBalanceRecord record = runUserBalanceRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runUserBalanceRecordMapper.deleteByPrimaryKey(id);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> delAllRunUserBalanceRecordByUID(Integer uid) {
		RunUserBalanceRecordExample example = new RunUserBalanceRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserBalanceRecord> list = runUserBalanceRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunUserBalanceRecordExample example1 = new RunUserBalanceRecordExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runUserBalanceRecordMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunUserBalanceRecordByID(Integer id) {
		RunUserBalanceRecord record = runUserBalanceRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> getAllRunUserBalanceRecordByUID(Integer uid) {
		RunUserBalanceRecordExample example = new RunUserBalanceRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserBalanceRecord> list = runUserBalanceRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
