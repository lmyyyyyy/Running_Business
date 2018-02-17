package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryDistance;

public interface RunDeliveryDistanceService {

    /**
     * 保存配送距离
     *
     * @param distance
     * @throws AppException
     */
    void saveRunDeliveryDistance(RunDeliveryDistance distance) throws AppException;

    /**
     * 更新配送距离
     *
     * @param distance
     * @throws AppException
     */
    void updateRunDeliveryDistance(RunDeliveryDistance distance) throws AppException;

    /**
     * 根据配送员id删除配送距离
     *
     * @param did
     * @throws AppException
     */
    void deleteRunDeliveryDistanceByDID(Integer did) throws AppException;

    /**
     * 根据配送员id获取配送距离
     *
     * @param did
     * @return
     * @throws AppException
     */
    RunDeliveryDistance getRunDeliveryDistanceByDID(Integer did) throws AppException;

}
