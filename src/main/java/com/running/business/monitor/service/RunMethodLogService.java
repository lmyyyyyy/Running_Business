package com.running.business.monitor.service;

import com.running.business.exception.AppException;
import com.running.business.monitor.pojo.RunMapperLogWithBLOBs;
import com.running.business.monitor.pojo.RunServiceLogWithBLOBs;

import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-21 下午3:40
 */
public interface RunMethodLogService {

    /**
     * 保存service方法日志
     *
     * @param runServiceLogWithBLOBs
     * @return
     * @throws AppException
     */
    int saveServiceLog(RunServiceLogWithBLOBs runServiceLogWithBLOBs) throws AppException;

    /**
     * 更新service方法日志
     *
     * @param runServiceLogWithBLOBs
     * @throws AppException
     */
    void updateServiceLog(RunServiceLogWithBLOBs runServiceLogWithBLOBs) throws AppException;

    /**
     * 批量保存service方法日志
     *
     * @param runServiceLogWithBLOBsList
     * @throws AppException
     */
    void batchSaveServiceLog(List<RunServiceLogWithBLOBs> runServiceLogWithBLOBsList) throws AppException;

    /**
     * 保存mapper方法日志
     *
     * @param runMapperLogWithBLOBs
     * @return
     * @throws AppException
     */
    int saveMapperLog(RunMapperLogWithBLOBs runMapperLogWithBLOBs) throws AppException;

    /**
     * 更新mapper方法日志
     *
     * @param runMapperLogWithBLOBs
     * @throws AppException
     */
    void updateMapperLog(RunMapperLogWithBLOBs runMapperLogWithBLOBs) throws AppException;

    /**
     * 批量保存mapper方法日志
     *
     * @param runMapperLogWithBLOBs
     * @throws AppException
     */
    void batchSaveMapperLog(List<RunMapperLogWithBLOBs> runMapperLogWithBLOBs) throws AppException;
}
