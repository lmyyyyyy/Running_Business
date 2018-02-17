package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserAddress;

public interface RunUserAddressService {

    /**
     * 保存用户地址
     *
     * @param userAddress
     */
    void saveRunUserAddress(RunUserAddress userAddress) throws AppException;

    /**
     * 更新用户地址
     *
     * @param userAddress
     */
    void updateRunUserAddress(RunUserAddress userAddress) throws AppException;

    /**
     * 根据id删除用户地址
     *
     * @param id
     */
    void deleteRunUserAddressByID(Integer id) throws AppException;

    /**
     * 根据用户id删除所有地址
     *
     * @param uid
     * @return
     */
    void deleteAllRunUserAddressByUID(Integer uid) throws AppException;

    /**
     * 根据id获取地址
     *
     * @param id
     * @return
     */
    BaseResult getRunUserAddressByID(Integer id) throws AppException;

    /**
     * 获取该用户的所有地址信息
     *
     * @param uid
     * @return
     */
    BaseResult getAllRunUserAddress(Integer uid) throws AppException;
}
