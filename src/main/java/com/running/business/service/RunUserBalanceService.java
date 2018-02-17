package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserBalance;

public interface RunUserBalanceService {

    /**
     * 保存用户余额
     *
     * @param runUserBalance
     * @throws AppException
     */
    void saveRunUserBalance(RunUserBalance runUserBalance) throws AppException;

    /**
     * 更新用户余额
     *
     * @param runUserBalance
     * @throws AppException
     */
    void updateRunUserBalance(RunUserBalance runUserBalance) throws AppException;

    /**
     * 根据id删除余额
     *
     * @param id
     * @throws AppException
     */
    void deleteRunUserBalance(Integer id) throws AppException;

    /**
     * 根据用户id获取余额
     *
     * @param uid
     * @return
     * @throws AppException
     */
    RunUserBalance getRunUserBalanceByUID(Integer uid) throws AppException;

}
