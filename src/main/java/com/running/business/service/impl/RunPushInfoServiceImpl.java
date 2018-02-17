package com.running.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunPushInfoMapper;
import com.running.business.pojo.RunPushInfo;
import com.running.business.pojo.RunPushInfoExample;
import com.running.business.service.RunPushInfoService;
import com.running.business.util.DateUtil;
import com.running.business.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RunPushInfoServiceImpl implements RunPushInfoService {

    @Autowired
    private RunPushInfoMapper runPushInfoMapper;

    /**
     * 添加推送信息
     *
     * @param pushInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult saveRunPushInfo(RunPushInfo pushInfo) throws AppException {
        if (pushInfo == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        pushInfo.setIsDelete(pushInfo.getIsDelete() == null || "".equals(pushInfo.getIsDelete()) ? false : pushInfo.getIsDelete());
        pushInfo.setPushTime(new Date());
        pushInfo.setUpdateTime(new Date());
        pushInfo.setPushStatus(pushInfo.getPushStatus() == null ? 0 : pushInfo.getPushStatus());
        runPushInfoMapper.insert(pushInfo);
        return BaseResult.success();
    }

    /**
     * 更新推送消息
     *
     * @param pushInfo
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult updatePushInfo(RunPushInfo pushInfo) throws AppException {
        if (pushInfo == null) {
            throw new AppException(ResultEnum.INFO_IS_EMPTY);
        }
        pushInfo.setUpdateTime(new Date());
        runPushInfoMapper.updateByPrimaryKeySelective(pushInfo);
        return BaseResult.success(pushInfo);
    }

    /**
     * 修改推送状态
     *
     * @param id
     * @param status
     * @param operatorId
     * @throws AppException
     */
    @Override
    public void updateStatus(Integer id, Integer status, Integer operatorId) throws AppException {
        if (status == null || status < 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INFO_ID_IS_ERROR);
        }
        RunPushInfo info = new RunPushInfo();
        info.setPid(id);
        info.setPushStatus(status);
        info.setOperator(operatorId);
        info.setUpdateTime(new Date());
        runPushInfoMapper.updateByPrimaryKeySelective(info);
    }

    /**
     * 根据id删除推送信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult deletePushInfoByID(Integer id) throws AppException {
        RunPushInfo pushInfo = runPushInfoMapper.selectByPrimaryKey(id);
        if (pushInfo == null) {
            return BaseResult.fail(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        pushInfo.setIsDelete(true);
        runPushInfoMapper.updateByPrimaryKeySelective(pushInfo);
        //runPushInfoMapper.deleteByPrimaryKey(id);
        return BaseResult.success(pushInfo);
    }

    /**
     * 根据id查询推送信息
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunPushInfo getRunPushInfoByID(Integer id) throws AppException {
        RunPushInfo pushInfo = runPushInfoMapper.selectByPrimaryKey(id);
        if (pushInfo == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return pushInfo;
    }

    /**
     * 获取所有推送信息
     *
     * @return
     * @throws AppException
     */
    @Override
    public BaseResult getAllRunPushInfo() throws AppException {
        RunPushInfoExample example = new RunPushInfoExample();
        List<RunPushInfo> list = runPushInfoMapper.selectByExampleWithBLOBs(example);
        if (!ValidateUtil.isValid(list)) {
            throw new AppException(ResultEnum.NOT_MSG.getCode(), ResultEnum.NOT_MSG.getMsg());
        }
        return BaseResult.success(list);
    }

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
    @Override
    public PageInfo<RunPushInfo> pagePushInfoByKeyword(String keyword, Integer page, Integer size, Integer status, Long beginTime, Long endTime, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 10;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "push_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderType = "DESC";
        }
        if (endTime == null || endTime == 0L) {
            endTime = System.currentTimeMillis();
        }
        if (beginTime == null || beginTime == 0L) {
            beginTime = endTime - 2592000000L;
        }
        PageHelper.startPage(page, size);
        RunPushInfoExample example = new RunPushInfoExample();
        RunPushInfoExample.Criteria criteria = example.createCriteria();
        criteria.andIsDeleteEqualTo(false);
        criteria.andPushStatusEqualTo(status);
        criteria.andPushTimeBetween(DateUtil.ms2Date(beginTime), DateUtil.ms2Date(endTime));
        if (keyword != null && !"".equals(keyword.trim())) {
            criteria.andPushTitleLike("%" + keyword.trim() + "%");
        }
        example.setOrderByClause(" " + orderField + " " + orderType);
        List<RunPushInfo> runPushInfos = runPushInfoMapper.selectByExampleWithBLOBs(example);
        return new PageInfo<>(runPushInfos);
    }
}
