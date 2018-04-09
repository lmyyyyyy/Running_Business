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
}
