package com.running.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.running.business.common.BaseResult;
import com.running.business.common.ErrorMsg;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryRecordMapper;
import com.running.business.pojo.RunDeliveryRecord;
import com.running.business.pojo.RunDeliveryRecordExample;
import com.running.business.pojo.RunDeliveryRecordExample.Criteria;
import com.running.business.service.RunDeliveryRecordService;
import com.running.business.util.ValidateUtil;

@Service
public class RunDeliveryRecordServiceImpl implements RunDeliveryRecordService{

	@Autowired
	private RunDeliveryRecordMapper runDeliveryRecordMapper;

	@Override
	public BaseResult<Object> addRunDeliveryRecord(RunDeliveryRecord record) {
		runDeliveryRecordMapper.insert(record);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> updateRunDeliveryRecord(RunDeliveryRecord record) {
		runDeliveryRecordMapper.updateByPrimaryKey(record);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> delRunDeliveryRecordByID(Integer id) {
		RunDeliveryRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		runDeliveryRecordMapper.deleteByPrimaryKey(id);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> delAllRunDeliveryRecordByDID(Integer did) {
		RunDeliveryRecordExample example = new RunDeliveryRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryRecord> list = runDeliveryRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg()));
		}
		RunDeliveryRecordExample example1 = new RunDeliveryRecordExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runDeliveryRecordMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult<Object> getRunDeliveryRecordByID(Integer id) {
		RunDeliveryRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg()));
		}
		return BaseResult.success(record);
	}

	@Override
	public BaseResult<Object> getAllDeliveryRecordByDID(Integer did) {
		RunDeliveryRecordExample example = new RunDeliveryRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryRecord> list = runDeliveryRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(new ErrorMsg(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg()));
		}
		return BaseResult.success(list);
	}
}
