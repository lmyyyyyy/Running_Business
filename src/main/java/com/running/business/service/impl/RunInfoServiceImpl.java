package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.dto.InfoDTO;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunInfoMapper;
import com.running.business.pojo.RunInfo;
import com.running.business.pojo.RunInfoExample;
import com.running.business.service.RunInfoService;
import com.running.business.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunInfoServiceImpl implements RunInfoService {

    @Autowired
    private RunInfoMapper runInfoMapper;

    /**
     * 添加滚动信息
     *
     * @param runInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult saveRunInfo(RunInfo runInfo) throws AppException {
        if (runInfo == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        runInfo.setAddTime(new Date());
        runInfoMapper.insert(runInfo);
        return BaseResult.success();
    }

    /**
     * 批量增加滚动信息
     *
     * @param infoDTOS
     * @throws AppException
     */
    @Override
    public void batchSaveRunInfo(List<InfoDTO> infoDTOS) throws AppException {

        if (infoDTOS == null || infoDTOS.size() == 0) {
            return;
        }
        for (InfoDTO infoDTO : infoDTOS) {
            if (infoDTO == null) {
                continue;
            }
            RunInfo info = new RunInfo();
            info.setContent(infoDTO.getContent());
            info.setInfoTime(infoDTO.getInfoTime());
            this.saveRunInfo(info);
        }
    }

    /**
     * 更新滚动信息
     *
     * @param runInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult updateRunInfo(RunInfo runInfo) throws AppException {
        if (runInfo == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        runInfoMapper.updateByPrimaryKey(runInfo);
        return BaseResult.success(runInfo);
    }

    /**
     * 删除滚动信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult deleteRunInfoByID(Integer id) throws AppException {
        RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
        if (runInfo == null) {
            return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runInfoMapper.deleteByPrimaryKey(id);
        return BaseResult.success(runInfo);
    }

    /**
     * 删除所有滚动信息
     *
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult deleteAllRunInfo() throws AppException {
        RunInfoExample example = new RunInfoExample();
        runInfoMapper.deleteByExample(example);
        return BaseResult.success();
    }

    /**
     * 根据id查询滚动信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunInfo getRunInfoByID(Integer id) throws AppException {
        RunInfo runInfo = runInfoMapper.selectByPrimaryKey(id);
        if (runInfo == null) {
            return null;
        }
        return runInfo;
    }

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
    @Override
    public PageInfo<RunInfo> pageRunInfo(Integer size, Integer page, Long beginTime, Long endTime, String orderField, String orderType) throws AppException {
        if (size == null || size <= 0) {
            size = 10;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DECS";
        }
        if (endTime == null || endTime == 0L) {
            endTime = System.currentTimeMillis();
        }
        if (beginTime == null || beginTime == 0L) {
            beginTime = endTime - 2592000000L;
        }
        PageHelper.startPage(page, size);
        RunInfoExample example = new RunInfoExample();
        RunInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAddTimeBetween(DateUtil.ms2Date(beginTime), DateUtil.ms2Date(endTime));
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RunInfo> list = runInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

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
    @Override
    public PageInfo<RunInfo> pageRunInfoBykeyword(String keyword, Integer size, Integer page, Long beginTime, Long endTime, String orderField, String orderType) throws AppException {
        if (size == null || size <= 0) {
            size = 10;
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "add_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DECS";
        }
        if (endTime == null || endTime == 0L) {
            endTime = System.currentTimeMillis();
        }
        if (beginTime == null || beginTime == 0L) {
            beginTime = endTime - 2592000000L;
        }
        PageHelper.startPage(page, size);
        RunInfoExample example = new RunInfoExample();
        RunInfoExample.Criteria criteria = example.createCriteria();
        criteria.andAddTimeBetween(DateUtil.ms2Date(beginTime), DateUtil.ms2Date(endTime));
        example.setOrderByClause(" " + orderField + " " + orderType);
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andContentLike("%" + keyword.trim() + "%");
        }
        List<RunInfo> list = runInfoMapper.selectByExample(example);
        return new PageInfo<>(list);
    }

    /**
     * 滚动播放消息
     *
     * @return
     * @throws AppException
     */
    @Override
    public List<RunInfo> rollingInfo() throws AppException {
        RunInfoExample example = new RunInfoExample();
        RunInfoExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause(" add_time DESC");
        Long endTime = System.currentTimeMillis();
        Long beginTime = endTime - 86400 * 1000L;
        criteria.andAddTimeBetween(DateUtil.ms2Date(beginTime), DateUtil.ms2Date(endTime));
        return runInfoMapper.selectByExample(example);
    }


}
