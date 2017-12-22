package com.running.business.service.impl;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserInfoMapper;
import com.running.business.pojo.RunUserExample;
import com.running.business.pojo.RunUserInfo;
import com.running.business.pojo.RunUserInfoExample;
import com.running.business.service.RunUserInfoService;
import com.running.business.util.ValidateUtil;
import com.running.business.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RunUserInfoServiceImpl implements RunUserInfoService{

	@Autowired
	private RunUserInfoMapper runUserInfoMapper;

	/**
	 * 添加用户详细信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	@Override
	public BaseResult addRunUserInfo(RunUserInfo userInfo) throws AppException {
		runUserInfoMapper.insert(userInfo);
		return BaseResult.success();
	}

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	@Override
	public BaseResult updateRunUserInfo(RunUserInfo userInfo) throws AppException {
		if (userInfo == null) {
			throw new AppException(ResultEnum.USER_INFO_ISEMPTY);
		}
		runUserInfoMapper.updateByPrimaryKey(userInfo);
		return BaseResult.success();
	}

	@Override
	public BaseResult delRunUserInfo(Integer uid) throws AppException {
		runUserInfoMapper.deleteByPrimaryKey(uid);
		return BaseResult.success();
	}

	/**
	 * 根据用户id获取用户信息
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	@Override
	public RunUserInfo getRunUserInfoById(Integer uid) throws AppException {
		RunUserInfo userInfo = runUserInfoMapper.selectByPrimaryKey(uid);
		if (userInfo == null) {
			throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
		}
		return userInfo;
	}

	/**
	 * 根据用户id获取UserVO
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	@Override
	public UserVO getUserVOById(Integer uid) throws AppException {
		UserVO userVO;
		RunUserInfo userInfo = this.getRunUserInfoById(uid);
		userVO = this.convertVO(userInfo);
		return userVO;
	}

	@Override
	public BaseResult getAllRunUserInfo() throws AppException {
		RunUserInfoExample example = new RunUserInfoExample();
		return BaseResult.success(runUserInfoMapper.selectByExample(example));
	}

	/**
	 * 检查系统生成昵称的唯一性，true为不存在可以使用，false为已存在不可使用
	 * @param name
	 * @return
	 * @throws AppException
	 */
	@Override
	public boolean checkNameUnique(String name) throws AppException {
		RunUserInfoExample example = new RunUserInfoExample();
		RunUserInfoExample.Criteria criteria = example.createCriteria();
		criteria.andUserNameEqualTo("游客_" + name);
		List<RunUserInfo> list = runUserInfoMapper.selectByExample(example);
		if (ValidateUtil.isValid(list)) {
			return false;
		}
		return true;
	}

	/**
	 * 将userInfo转换为UserVO
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	public UserVO convertVO(RunUserInfo userInfo) throws AppException {
		UserVO userVO = new UserVO();
		userVO.setUid(userInfo.getUid());
		userVO.setUserPhone(userInfo.getUserPhone());
		userVO.setUserAddress(userInfo.getUserAddress());
		userVO.setUserGender(userInfo.getUserGender());
		userVO.setUserName(userInfo.getUserName());
		userVO.setUserPhoto(userInfo.getUserPhoto());
		userVO.setUserPoint(userInfo.getUserPoint());
		return userVO;
	}
 }
