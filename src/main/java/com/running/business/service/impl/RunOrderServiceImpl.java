package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderExample;
import com.running.business.pojo.RunOrderExample.Criteria;
import com.running.business.service.RunOrderService;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunOrderServiceImpl implements RunOrderService{

	@Autowired
	private RunOrderMapper runOrderMapper;

	@Override
	public BaseResult addRunOrder(RunOrder order) {
		runOrderMapper.insert(order);
		return BaseResult.success();
	}

	@Override
	public BaseResult updateRunOrder(RunOrder order) {
		runOrderMapper.updateByPrimaryKey(order);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult delRunOrderByOID(String oid) {
		RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		runOrderMapper.deleteByPrimaryKey(oid);
		return BaseResult.success(order);
	}

	@Override
	public BaseResult delAllRunOrderByUID(Integer uid) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderExample example1 = new RunOrderExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andUidEqualTo(uid);
		runOrderMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult delAllRunOrderByDID(Integer did) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
		}
		RunOrderExample example1 = new RunOrderExample();
		Criteria criteria1 = example1.createCriteria();
		criteria1.andDidEqualTo(did);
		runOrderMapper.deleteByExample(example1);
		return BaseResult.success();
	}

	@Override
	public BaseResult getRunOrderByOID(String oid) {
		RunOrder order = runOrderMapper.selectByPrimaryKey(oid);
		if (order == null) {
			return BaseResult.fail(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return BaseResult.success(order);
	}

	@Override
	public BaseResult getAllRunOrderByUID(Integer uid) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	/**
	 * 根据用户ID查询订单
	 * @param uid 用户ID
	 * @param currentPage 当前页
	 * @return
	 */
	@Override
	public BaseResult getAllRunOrderByUID(Integer uid, Integer currentPage) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(uid);
		PageHelper.startPage(currentPage, 20);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		PageInfo<RunOrder> pageInfo=new PageInfo<RunOrder>(list);
		return BaseResult.success(pageInfo);
	}

	@Override
	public BaseResult getAllRunOrderByDID(Integer did) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	/**
	 * 根据配送员ID查询订单
	 * @param did 配送员ID
	 * @param currentPage 当前页
	 * @return
	 */
	@Override
	public BaseResult getAllRunOrderByDID(Integer did, Integer currentPage) {
		RunOrderExample example = new RunOrderExample();
		Criteria criteria = example.createCriteria();
		criteria.andDidEqualTo(did);
		PageHelper.startPage(currentPage, 20);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		PageInfo<RunOrder> pageInfo = new PageInfo<>(list);
		return BaseResult.success(pageInfo);
	}

	@Override
	public BaseResult getAllRunOrder() {
		RunOrderExample example = new RunOrderExample();
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		return BaseResult.success(list);
	}

	/**
	 * 根据用户ID，订单状态查询订单。
	 * @param id 用户ID
	 * @param status 订单状态
	 * @param currentPage 当前页
	 * @return
	 */
	@Override
	public BaseResult getOrderByUIDAndStatus(int id, int status, int currentPage) {
		RunOrderExample example = new RunOrderExample();
		example.setOrderByClause("add_time " + "DESC");
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(id);
		criteria.andStatusEqualTo(status);
		PageHelper.startPage(currentPage, 20);
		List<RunOrder> list = runOrderMapper.selectByExample(example);
		if (!ValidateUtil.isValid(list)) {
			return BaseResult.fail(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
		}
		PageInfo<RunOrder> pageInfo = new PageInfo<>(list);
		return BaseResult.success(pageInfo);
	}

}
