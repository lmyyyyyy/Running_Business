package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunDeliveryBalanceRecordMapper;
import com.running.business.pojo.RunDeliveryBalanceRecord;
import com.running.business.pojo.RunDeliveryBalanceRecordExample;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunDeliveryBalanceRecordServiceImpl implements RunDeliveryBalanceRecordService{

	@Autowired
	private RunDeliveryBalanceRecordMapper runDeliveryRecordMapper;

	@Override
	public BaseResult addRunDeliveryRecord(RunDeliveryBalanceRecord record) {
		runDeliveryRecordMapper.insert(record);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunDeliveryRecord(RunDeliveryBalanceRecord record) {
		runDeliveryRecordMapper.updateByPrimaryKey(record);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult delRunDeliveryRecordByID(Integer id) {
		RunDeliveryBalanceRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryRecordMapper.deleteByPrimaryKey(id);
		return BaseResult.success(record);
	}

	@Override
	public BaseResult delAllRunDeliveryRecordByDID(Integer did) {
		RunDeliveryBalanceRecordExample example = new RunDeliveryBalanceRecordExample();
		RunDeliveryBalanceRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryBalanceRecord> list = runDeliveryRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunDeliveryBalanceRecordExample example1 = new RunDeliveryBalanceRecordExample();
		RunDeliveryBalanceRecordExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runDeliveryRecordMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunDeliveryRecordByID(Integer id) {
		RunDeliveryBalanceRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(record);
	}

	@Override
	public BaseResult getAllDeliveryRecordByDID(Integer did) {
		RunDeliveryBalanceRecordExample example = new RunDeliveryBalanceRecordExample();
		RunDeliveryBalanceRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunDeliveryBalanceRecord> list = runDeliveryRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}
}
