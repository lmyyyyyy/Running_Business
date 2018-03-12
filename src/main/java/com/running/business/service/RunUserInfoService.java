package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserInfo;
import com.running.business.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

public interface RunUserInfoService {

	/**
	 * 添加用户详细信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	void saveRunUserInfo(RunUserInfo userInfo) throws AppException;

	/**
	 * 更新用户信息
	 * @param userInfo
	 * @return
	 * @throws AppException
	 */
	void updateRunUserInfo(RunUserInfo userInfo) throws AppException;

	/**
	 * 根据用户id删除用户信息
	 *
	 * @param uid
	 * @throws AppException
	 */
	void deleteRunUserInfo(Integer uid) throws AppException;

	/**
	 * 根据用户id获取用户信息
	 *
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	RunUserInfo getRunUserInfoById(Integer uid) throws AppException;

	/**
	 * 根据id获取用户vo
	 * @param uid
	 * @return
	 * @throws AppException
	 */
	UserVO getUserVOById(Integer uid) throws AppException;

	/**
	 * 获取所有用户信息
	 *
	 * @return
	 * @throws AppException
	 */
	BaseResult getAllRunUserInfo() throws AppException;

	/**
	 * 检查系统随机生成昵称唯一性
	 * @param name
	 * @return
	 * @throws AppException
	 */
	boolean checkNameUnique(String name) throws AppException;

	/**
	 * 上传用户头像
	 * @param file
	 * @return
	 */
	BaseResult uploadUserImg(MultipartFile file, Integer uid);
}
