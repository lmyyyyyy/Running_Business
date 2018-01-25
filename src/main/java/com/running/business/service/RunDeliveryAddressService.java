package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryAddress;

import java.util.List;

public interface RunDeliveryAddressService {

    /**
     * 添加地址
     *
     * @param address
     * @return
     */
    Integer saveRunDeliveryAddress(RunDeliveryAddress address) throws AppException;

    /**
     * 更新地址
     *
     * @param address
     * @return
     */
    void updateRunDeliveryAddress(RunDeliveryAddress address) throws AppException;

    /**
     * 根据id删除地址
     *
     * @param id
     * @return
     */
    void deleteRunDeliveryAddressByID(Integer id) throws AppException;

    /**
     * 根据did删除所有地址
     *
     * @param did
     * @return
     */
    void deleteAllRunDeliveryAddressByDID(Integer did) throws AppException;

    /**
     * 根据id获取地址信息
     *
     * @param id
     * @return
     */
    RunDeliveryAddress getRunDeliveryAddressByID(Integer id) throws AppException;

    /**
     * 根据did获取所有配送员地址
     *
     * @param did
     * @return
     */
    List<RunDeliveryAddress> getAllRunDeliveryAddressByDID(Integer did) throws AppException;
}
