package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.vo.AdminVO;

public interface RunAdminInfoService {

    /**
     * 添加管理员信息
     *
     * @param adminInfo
     * @return
     * @throws AppException
     */
    BaseResult insertRunAdminInfo(RunAdminInfo adminInfo) throws AppException;

    /**
     * 更新管理员信息
     *
     * @param adminInfo
     * @return
     * @throws AppException
     */
    BaseResult updateRunAdminInfo(RunAdminInfo adminInfo) throws AppException;

    /**
     * 根据id删除管理员
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult delRunAdminInfoByID(Integer id) throws AppException;

    /**
     * 根据id获取管理员信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult getRunAdminInfoByID(Integer id) throws AppException;

    /**
     * 检查姓名是否重复
     *
     * @param name
     * @return
     * @throws AppException
     */
    boolean checkNameUnique(String name) throws AppException;

    /**
     * 根据id获取AdminVO
     *
     * @param id
     * @return
     * @throws AppException
     */
    AdminVO getAdminVOById(Integer id) throws AppException;
}
