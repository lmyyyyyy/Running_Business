package com.running.business.service;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.dto.InfoDTO;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunInfo;

import java.util.List;

public interface RunInfoService {

    /**
     * 添加滚动信息
     *
     * @param runInfo
     * @return
     * @throws AppException
     */
    BaseResult saveRunInfo(RunInfo runInfo) throws AppException;

    /**
     * 批量增加滚动信息
     *
     * @param infoDTOS
     * @throws AppException
     */
    void batchSaveRunInfo(List<InfoDTO> infoDTOS) throws AppException;

    /**
     * 更新滚动信息
     *
     * @param runInfo
     * @return
     * @throws AppException
     */
    BaseResult updateRunInfo(RunInfo runInfo) throws AppException;

    /**
     * 删除滚动信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    BaseResult deleteRunInfoByID(Integer id) throws AppException;

    /**
     * 删除所有滚动信息
     *
     * @return
     * @throws AppException
     */
    BaseResult deleteAllRunInfo() throws AppException;

    /**
     * 根据id查询滚动信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    RunInfo getRunInfoByID(Integer id) throws AppException;

    /**
     * 分页获取滚动信息
     *
     * @param size
     * @param page
     * @param beginTime
     * @param endTime
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RunInfo> pageRunInfo(Integer size, Integer page, Long beginTime, Long endTime, String orderField, String orderType) throws AppException;

    /**
     * 分页模糊查询滚动信息
     *
     * @param keyword
     * @param size
     * @param page
     * @param beginTime
     * @param endTime
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    PageInfo<RunInfo> pageRunInfoBykeyword(String keyword, Integer size, Integer page, Long beginTime, Long endTime, String orderField, String orderType) throws AppException;

    /**
     * 滚动播放消息
     *
     * @return
     * @throws AppException
     */
    List<RunInfo> rollingInfo() throws AppException;
}
