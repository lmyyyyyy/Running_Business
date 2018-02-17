package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryBalanceRecordMapper;
import com.running.business.pojo.RunDeliveryBalanceRecord;
import com.running.business.pojo.RunDeliveryBalanceRecordExample;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunDeliveryBalanceRecordServiceImpl implements RunDeliveryBalanceRecordService{

	@Autowired
	private RunDeliveryBalanceRecordMapper runDeliveryRecordMapper;

	/**
	 * 保存配送员余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	@Override
	public void saveRunDeliveryRecord(RunDeliveryBalanceRecord record) throws AppException {
		if (record == null) {
			throw new AppException(ResultEnum.DELIVERY_BALANCE_RECORD_EMPTY);
		}
		record.setAddTime(new Date());
		runDeliveryRecordMapper.insert(record);
	}

	/**
	 * 更新配送员余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	@Override
	public void updateRunDeliveryRecord(RunDeliveryBalanceRecord record) throws AppException  {
		if (record == null) {
			throw new AppException(ResultEnum.DELIVERY_BALANCE_RECORD_EMPTY);
		}
		runDeliveryRecordMapper.updateByPrimaryKey(record);
	}

	/**
	 * 根据id删除配送员记录
	 *
	 * @param id
	 * @throws AppException
	 */
	@Override
	public void deleteRunDeliveryRecordByID(Integer id) throws AppException  {
		RunDeliveryBalanceRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runDeliveryRecordMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据配送员id删除所有余额记录
	 *
	 * @param did
	 * @throws AppException
	 */
	@Override
	public void deleteAllRunDeliveryRecordByDID(Integer did) throws AppException  {
		RunDeliveryBalanceRecordExample example1 = new RunDeliveryBalanceRecordExample();
		RunDeliveryBalanceRecordExample.Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runDeliveryRecordMapper.deleteByExample(example1);
	}

	/**
	 * 根据id获取配送员余额记录
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	@Override
	public RunDeliveryBalanceRecord getRunDeliveryRecordByID(Integer id) throws AppException  {
		RunDeliveryBalanceRecord record = runDeliveryRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return record;
	}

	/**
	 * 根据配送员id分页获取余额记录
	 *
	 * @param did
	 * @param page
	 * @param size
	 * @param orderField
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	@Override
	public PageInfo<RunDeliveryBalanceRecord> pageAllDeliveryRecordByDID(Integer did, Integer page, Integer size, String orderField, String orderType) throws AppException  {
		if (page == null || page <= 0) {
			page = 1;
		}
		if (size == null || size <= 0) {
			size = 10;
		}
		if (orderField == null || "".equals(orderField)) {
			orderField = "add_time";
		}
		if (orderType == null || "".equals(orderType)) {
			orderType = "DESC";
		}
		PageHelper.startPage(page, size);
		RunDeliveryBalanceRecordExample example = new RunDeliveryBalanceRecordExample();
		RunDeliveryBalanceRecordExample.Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		example.setOrderByClause(" " + orderField + " " + orderType);
		List<RunDeliveryBalanceRecord> list = runDeliveryRecordMapper.selectByExample(example);
		return new PageInfo<>(list);
	}
}
