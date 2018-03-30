package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunDeliveryDistanceMapper;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.service.RunDeliveryDistanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RunDeliveryDistanceServiceImpl implements RunDeliveryDistanceService {

    @Autowired
    private RunDeliveryDistanceMapper runDeliveryDistanceMapper;

    /**
     * 保存配送距离
     *
     * @param distance
     * @throws AppException
     */
    @Override
    public void saveRunDeliveryDistance(RunDeliveryDistance distance) throws AppException {
        if (distance == null) {
            throw new AppException(ResultEnum.DELIVERY_DISTANCE_INFO_EMPTY);
        }
        runDeliveryDistanceMapper.insert(distance);
    }

    /**
     * 更新配送距离
     *
     * @param distance
     * @throws AppException
     */
    @Override
    public void updateRunDeliveryDistance(RunDeliveryDistance distance) throws AppException {
        if (distance == null) {
            throw new AppException(ResultEnum.DELIVERY_DISTANCE_INFO_EMPTY);
        }
        distance.setUpdateTime(new Date());
        runDeliveryDistanceMapper.updateByPrimaryKeySelective(distance);
    }

    /**
     * 根据配送员id删除配送距离
     *
     * @param did
     * @throws AppException
     */
    @Override
    public void deleteRunDeliveryDistanceByDID(Integer did) throws AppException {
        RunDeliveryDistance distance = runDeliveryDistanceMapper.selectByPrimaryKey(did);
        if (distance == null) {
            throw new AppException(ResultEnum.DEL_ERROR.getCode(), ResultEnum.DEL_ERROR.getMsg());
        }
        runDeliveryDistanceMapper.deleteByPrimaryKey(did);
    }

    /**
     * 根据配送员id获取配送距离
     *
     * @param did
     * @return
     * @throws AppException
     */
    @Override
    public RunDeliveryDistance getRunDeliveryDistanceByDID(Integer did) throws AppException {
        RunDeliveryDistance distance = runDeliveryDistanceMapper.selectByPrimaryKey(did);

        return distance;
    }
}
