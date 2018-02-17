package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserBalanceRecordMapper;
import com.running.business.pojo.RunUserBalanceRecord;
import com.running.business.pojo.RunUserBalanceRecordExample;
import com.running.business.pojo.RunUserBalanceRecordExample.Criteria;
import com.running.business.service.RunUserBalanceRecordService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunUserBalanceRecordServiceImpl implements RunUserBalanceRecordService{

	@Autowired
	private RunUserBalanceRecordMapper runUserBalanceRecordMapper;

	/**
	 * 保存用户余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	@Override
	public void saveRunUserBalanceRecord(RunUserBalanceRecord record) throws AppException {
		if (record == null) {
			throw new AppException(ResultEnum.USER_BALANCE_RECORD_INFO_EMPTY);
		}
		runUserBalanceRecordMapper.insert(record);
	}

	/**
	 * 更新用户余额记录
	 *
	 * @param record
	 * @throws AppException
	 */
	@Override
	public void updateRunUserBalanceRecord(RunUserBalanceRecord record) throws AppException {
		if (record == null) {
			throw new AppException(ResultEnum.USER_BALANCE_RECORD_INFO_EMPTY);
		}
		runUserBalanceRecordMapper.updateByPrimaryKeySelective(record);
	}

	/**
	 * 根据用户id删除用户余额记录
	 *
	 * @param id
	 * @throws AppException
	 */
	@Override
	public void deleteRunUserBalanceRecordByID(Integer id) throws AppException {
		RunUserBalanceRecord record = runUserBalanceRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runUserBalanceRecordMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 根据用户ID删除所有用户余额记录
	 *
	 * @param uid
	 * @throws AppException
	 */
	@Override
	public void deleteAllRunUserBalanceRecordByUID(Integer uid) throws AppException {
		RunUserBalanceRecordExample example = new RunUserBalanceRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunUserBalanceRecord> list = runUserBalanceRecordMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunUserBalanceRecordExample example1 = new RunUserBalanceRecordExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runUserBalanceRecordMapper.deleteByExample(example1);
	}

	/**
	 * 根据id获取余额记录
	 *
	 * @param id
	 * @return
	 * @throws AppException
	 */
	@Override
	public RunUserBalanceRecord queryRunUserBalanceRecordByID(Integer id) throws AppException {
		RunUserBalanceRecord record = runUserBalanceRecordMapper.selectByPrimaryKey(id);
		if (record == null) {
			throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return record;
	}

	/**
	 * 根据用户id和流水号查询交易记录
	 *
	 * @param uid
	 * @param number
	 * @return
	 * @throws AppException
	 */
	@Override
	public RunUserBalanceRecord queryRunUserBalanceRecordByUID(Integer uid, String number) throws AppException {
		if (number == null) {
			throw new AppException(ResultEnum.USER_BALANCE_RECORD_NUMBER_ERROR);
		}
		RunUserBalanceRecordExample example = new RunUserBalanceRecordExample();
		RunUserBalanceRecordExample.Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		if (!"".equals(number)) {
			criteria.andNumberEqualTo(number);
		}
		List<RunUserBalanceRecord> records = runUserBalanceRecordMapper.selectByExample(example);
		if (records == null || records.isEmpty()) {
			return null;
		}
		return records.get(0);
	}

	/**
	 * 根据用户id分页获取余额记录
	 *
	 * @param uid
	 * @param page
	 * @param size
	 * @param orderField
	 * @param orderType
	 * @return
	 * @throws AppException
	 */
	@Override
	public PageInfo<RunUserBalanceRecord> pageAllRunUserBalanceRecordByUID(Integer uid, Integer page, Integer size, String orderField, String orderType) throws AppException {
		if(page == null || page <= 0) {
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
		RunUserBalanceRecordExample example = new RunUserBalanceRecordExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		example.setOrderByClause(" " + orderField + " " + orderType);
		List<RunUserBalanceRecord> list = runUserBalanceRecordMapper.selectByExample(example);
		return new PageInfo<>(list);
	}
}
