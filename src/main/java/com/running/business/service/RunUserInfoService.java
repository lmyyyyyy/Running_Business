package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserInfo;
import com.running.business.vo.UserVO;

public interface RunUserInfoService {

	/**
	 * 添加用户详细信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	BaseResult addRunUserInfo(RunUserInfo userInfo) throws AppException;

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	BaseResult updateRunUserInfo(RunUserInfo userInfo) throws AppException;
	BaseResult delRunUserInfo(Integer uid) throws AppException;
	
	RunUserInfo getRunUserInfoById(Integer uid) throws AppException;
	UserVO getUserVOById(Integer uid) throws AppException;
	BaseResult getAllRunUserInfo() throws AppException;

	/**
	 * 检查系统随机生成昵称唯一性
	 * @param name
	 * @return
	 * @throws AppException
	 */
	boolean checkNameUnique(String name) throws AppException;
}
