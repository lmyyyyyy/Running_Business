package com.running.business.monitor.service.impl;

import com.running.business.exception.AppException;
import com.running.business.monitor.mapper.RunMapperLogMapper;
import com.running.business.monitor.mapper.RunServiceLogMapper;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;
import com.running.business.monitor.service.RunMethodLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-21 下午3:41
 */
@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
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
            return;
        }
        if (runServiceLogWithBLOBs.getId() == null || runServiceLogWithBLOBs.getId() == 0) {
            return;
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
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunServiceLogWithBLOBs queryServiceLogById(Integer id) throws AppException {
        if (id == null || id <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        return runServiceLogMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据操作者id查询service日志
     *
     * @param oId
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogByOID(Integer oId, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (oId == null || oId <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorIdEqualTo(oId);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据执行状态查询service日志
     *
     * @param invokerStatus
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogByStatus(Integer invokerStatus, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (invokerStatus == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andInvokeStatusEqualTo(invokerStatus);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据父id查询service日志
     *
     * @param pId
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogByPID(Integer pId, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (pId == null || pId <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(pId);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据类名模糊查询Service日志
     *
     * @param className
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeClassName(String className, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (className == null || "".equals(className)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andClassNameLike("%" + className + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据方法名模糊查询Service日志
     *
     * @param methodName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeMethodName(String methodName, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (methodName == null || "".equals(methodName)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodNameLike("%" + methodName + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据操作者姓名查询Service日志
     *
     * @param operatorName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeOperatorName(String operatorName, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (operatorName == null || "".equals(operatorName)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andOperatorNameLike("%" + operatorName + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
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
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeMethodParam(String methodParam, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (methodParam == null || "".equals(methodParam)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodParamLike("%" + methodParam + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
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
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeReturnValue(String returnValue, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (returnValue == null || "".equals(returnValue)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andReturnValueLike("%" + returnValue + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
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
    public List<RunServiceLogWithBLOBs> pageServiceLogLikeErrorMessage(String errorMessage, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (errorMessage == null || "".equals(errorMessage)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andErrorMessageLike("%" + errorMessage + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 范围查询，根据执行时间查询startTime到endTime中间的日志
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogByMethodTime(Long startTime, Long endTime, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (startTime == null || startTime < 0 || endTime < startTime || endTime == null || endTime < 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andTimeCostBetween(startTime, endTime);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 范围查询，根据执行时间查询beginTime到endTime中间的日志
     *
     * @param beginTime
     * @param endTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunServiceLogWithBLOBs> pageServiceLogByExecutionTime(Date beginTime, Date endTime, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (beginTime == null || endTime == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunServiceLogExample example = new RunServiceLogExample();
        RunServiceLogExample.Criteria criteria = example.createCriteria();
        criteria.andAddTimeBetween(beginTime, endTime);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runServiceLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据ID查询Mapper日志
     *
     * @param id
     * @return
     * @throws AppException
     */
    @Override
    public RunMapperLogWithBLOBs queryMapperLogByID(Integer id) throws AppException {
        if (id == null || id < 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        return runMapperLogMapper.selectByPrimaryKey(id);
    }

    /**
     * service方法Id查询Mapper日志
     *
     * @param serviceLogId
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByServiceId(Integer serviceLogId, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (serviceLogId == null || serviceLogId <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andSerivceLogIdEqualTo(serviceLogId);

        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 操作类型查询Mapper日志
     *
     * @param OperateType
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByOperateType(Byte OperateType, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (OperateType == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andOperateTypeEqualTo(OperateType);

        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * service方法Id查询Mapper日志
     *
     * @param InfluenceRow
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByInfluenceRow(Integer InfluenceRow, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (InfluenceRow == null || InfluenceRow <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andInfluenceRowEqualTo(InfluenceRow);

        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 执行状态查询Mapper日志
     *
     * @param InvokeStatus
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByInvokeStatus(Byte InvokeStatus, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (InvokeStatus == null || InvokeStatus <= 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andInvokeStatusEqualTo(InvokeStatus);

        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 方法名查询Mapper日志
     *
     * @param methodName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByMethodName(String methodName, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (methodName == null || "".equals(methodName)) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodNameLike("%" + methodName + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据方法参数模糊查询Mapper日志
     *
     * @param methodParam
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByMethodParam(String methodParam, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andMethodParamLike("%" + methodParam + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 表名查询Mapper日志
     *
     * @param TargetTableName
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByTargetTableName(String TargetTableName, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (TargetTableName == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andTargetTableNameLike("%" + TargetTableName + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * sql语句查询Mapper日志
     *
     * @param sqlStatement
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogBySqlStatement(String sqlStatement, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andSqlStatementLike("%" + sqlStatement + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 根据错误信息模糊查询Mapper日志
     *
     * @param error
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByError(String error, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andErrorMessageLike("%" + error + "%");
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 方法耗时查询Mapper日志
     *
     * @param startTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByMethodTime(Date startTime, Date endTime, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (startTime == null || endTime == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andAddTimeBetween(startTime, endTime);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }

    /**
     * 范围查询Mapper日志
     *
     * @param startTime
     * @return
     * @throws AppException
     */
    @Override
    public List<RunMapperLogWithBLOBs> pageMapperLogByTimeCost(Long startTime, Long endTime, Integer page, Integer size, String orderField, String orderType) throws AppException {
        if (startTime == null || endTime == null) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        if (page == null || page <= 0) {
            page = 1;
        }
        if (size == null || size <= 0) {
            size = 20;
        }
        if (orderField == null || "".equals(orderField)) {
            orderField = "update_time";
        }
        if (orderType == null || "".equals(orderType)) {
            orderField = "DESC";
        }
        RunMapperLogExample example = new RunMapperLogExample();
        RunMapperLogExample.Criteria criteria = example.createCriteria();
        criteria.andTimeCostBetween(startTime, endTime);
        PageHelper.startPage(page, size);
        example.setOrderByClause(orderField + " " + orderType);
        return runMapperLogMapper.selectByExampleWithBLOBs(example);
    }
}


