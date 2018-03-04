package com.running.business.monitor.service.impl;

import com.running.business.exception.AppException;
import com.running.business.monitor.mapper.RunMapperLogMapper;
import com.running.business.monitor.mapper.RunServiceLogMapper;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.monitor.pojo.RunServiceLogExample;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import com.running.business.monitor.service.RunMethodLogService;
import com.running.business.common.ResultEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-21 下午3:41
 */
@Service
public class RunMethodLogServiceImpl implements RunMethodLogService {

    @Autowired
    private RunServiceLogMapper runServiceLogMapper;

    @Autowired
    private RunMapperLogMapper runMapperLogMapper;

    /**
     * 保存serivce日志
     *
     * @param runServiceLogWithBLOBs
     * @return
     * @throws AppException
     */
    @Override
    public int saveServiceLog(RunServiceLogWithBLOBs runServiceLogWithBLOBs) throws AppException {
        if (runServiceLogWithBLOBs == null) {
            return -1;
        }
        return runServiceLogMapper.insert(runServiceLogWithBLOBs);
    }

    /**
     * 更新service日志
     *
     * @param runServiceLogWithBLOBs
     * @throws AppException
     */
    @Override
    public void updateServiceLog(RunServiceLogWithBLOBs runServiceLogWithBLOBs) throws AppException {
        if (runServiceLogWithBLOBs == null) {

        }
        runServiceLogMapper.updateByPrimaryKeySelective(runServiceLogWithBLOBs);
    }

    /**
     * 批量保存service日志
     *
     * @param runServiceLogWithBLOBsList
     * @throws AppException
     */
    @Override
    public void batchSaveServiceLog(List<RunServiceLogWithBLOBs> runServiceLogWithBLOBsList) throws AppException {
        if (runServiceLogWithBLOBsList == null || runServiceLogWithBLOBsList.size() == 0) {
            return;
        }
        runServiceLogMapper.batchInsert(runServiceLogWithBLOBsList);
        if (runServiceLogWithBLOBsList.size() > 1) {
            runServiceLogMapper.batchUpdateParentId(runServiceLogWithBLOBsList);
        }
    }

    /**
     * 保存mapper日志
     *
     * @param runMapperLogWithBLOBs
     * @return
     * @throws AppException
     */
    @Override
    public int saveMapperLog(RunMapperLogWithBLOBs runMapperLogWithBLOBs) throws AppException {
        if (runMapperLogWithBLOBs == null) {
            return -1;
        }
        return runMapperLogMapper.insert(runMapperLogWithBLOBs);
    }

    /**
     * 更新mapper日志
     *
     * @param runMapperLogWithBLOBs
     * @throws AppException
     */
    @Override
    public void updateMapperLog(RunMapperLogWithBLOBs runMapperLogWithBLOBs) throws AppException {
        if (runMapperLogWithBLOBs == null) {

        }
        runMapperLogMapper.updateByPrimaryKeySelective(runMapperLogWithBLOBs);
    }

    /**
     * 批量保存mapper日志
     *
     * @param runMapperLogWithBLOBs
     * @throws AppException
     */
    @Override
    public void batchSaveMapperLog(List<RunMapperLogWithBLOBs> runMapperLogWithBLOBs) throws AppException {
        if (runMapperLogWithBLOBs == null || runMapperLogWithBLOBs.size() == 0) {
            return;
        }
        runMapperLogMapper.batchInsert(runMapperLogWithBLOBs);
    }

    /**
     * 根据id查询service日志
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunServiceLogWithBLOBs queryServiceLogById(Integer id) throws AppException{
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        return runServiceLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据操作者id查询service日志
     * @param oId
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogByOID(Integer oId) throws AppException{
        if(oId == null || oId <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorIdEqualTo(oId);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据执行状态查询service日志
     * @param invokerStatus
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogByStatus(Integer invokerStatus) throws AppException{
        if (invokerStatus == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andInvokeStatusEqualTo(invokerStatus);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据父id查询service日志
     * @param pId
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogByPID(Integer pId) throws AppException{
        if (pId == null || pId <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pId);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据类名模糊查询Service日志
     * @param className
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeClassName(String className) throws AppException{
        if (className == null || "".equals(className)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andClassNameLike(className);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据方法名模糊查询Service日志
     * @param methodName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeMethodName(String methodName) throws AppException{
        if (methodName == null || "".equals(methodName)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodNameLike(methodName);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据操作者姓名查询Service日志
     * @param operatorName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeOperatorName(String operatorName) throws AppException{
        if (operatorName == null || "".equals(operatorName)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorNameLike(operatorName);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据方法参数模糊查询Service日志
     *
     * @param methodParam
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeMethodParam(String methodParam) throws AppException {
        if (methodParam == null || "".equals(methodParam)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodParamLike(methodParam);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据返回值模糊查询Service日志
     *
     * @param returnValue
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeReturnValue(String returnValue) throws AppException {
        if (returnValue == null || "".equals(returnValue)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andReturnValueLike(returnValue);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据错误信息模糊查询Service日志
     *
     * @param errorMessage
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogLikeErrorMessage(String errorMessage) throws AppException {
        if (errorMessage == null || "".equals(errorMessage)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andErrorMessageLike(errorMessage);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 范围查询，根据执行时间查询startTime到endTime中间的日志
     * @param startTime
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogByMethodTime(Long startTime,Long endTime) throws AppException{
        if (startTime == null || startTime < 0 || endTime < startTime || endTime  == null || endTime < 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andTimeCostBetween(startTime,endTime);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 范围查询，根据执行时间查询startTime到endTime中间的日志
     * @param beginTime
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> queryServiceLogByExecutionTime(Date beginTime, Date endTime) throws AppException {
        if (beginTime == null || endTime  == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andAddTimeBetween(beginTime,endTime);
        example.setOrderByClause(" update_time desc");
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }
}
