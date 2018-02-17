package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunPushInfo;

public interface RunPushInfoService {

    /**
     * 添加推送信息
     *
     * @param pushInfo
     * @return
     * @throws AppException
     */
    BaseResult saveRunPushInfo(RunPushInfo pushInfo) throws AppException;

    /**
     * 更新推送消息
     *
     * @param pushInfo
     * @return
     * @throws AppException
     */
    BaseResult updatePushInfo(RunPushInfo pushInfo) throws AppException;

    /**
     * 修改推送状态
     *
     * @param id
     * @param status
     * @param operatorId
     * @throws AppException
     */
    void updateStatus(Integer id, Integer status, Integer operatorId) throws AppException;

    /**
     * 根据id删除推送信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult deletePushInfoByID(Integer id) throws AppException;

    /**
     * 根据id查询推送信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    RunPushInfo getRunPushInfoByID(Integer id) throws AppException;

    /**
     * 获取所有推送信息
     *
     * @return
     * @throws AppException
     */
    BaseResult getAllRunPushInfo() throws AppException;

    /**
     * 分页模糊查询推送信息
     *
     * @param keyword
     * @param page
     * @param size
     * @param beginTime
     * @param endTime
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RunPushInfo> pagePushInfoByKeyword(String keyword, Integer page, Integer size, Integer status, Long beginTime, Long endTime, String orderField, String orderType) throws AppException;

}
