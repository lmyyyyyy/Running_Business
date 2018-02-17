package com.running.business.service.impl;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunUserBalanceMapper;
import com.running.business.pojo.RunUserBalance;
import com.running.business.service.RunUserBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class RunUserBalanceServiceImpl implements RunUserBalanceService {

    @Autowired
    private RunUserBalanceMapper runUserBalanceMapper;

    /**
     * 保存用户余额
     *
     * @param runUserBalance
     * @throws AppException
     */
    @Override
    public void saveRunUserBalance(RunUserBalance runUserBalance) throws AppException {
        if (runUserBalance == null) {
            throw new AppException(ResultEnum.USER_BALANCE_INFO_EMPTY);
        }
        runUserBalanceMapper.insert(runUserBalance);
    }

    /**
     * 更新用户余额
     *
     * @param runUserBalance
     * @throws AppException
     */
    @Override
    public synchronized void updateRunUserBalance(RunUserBalance runUserBalance) throws AppException {
        if (runUserBalance == null) {
            throw new AppException(ResultEnum.USER_BALANCE_INFO_EMPTY);
        }
        runUserBalance.setUpdateTime(new Date());
        runUserBalanceMapper.updateByPrimaryKeySelective(runUserBalance);
    }

    /**
     * 根据id删除用户余额
     *
     * @param id
     * @throws AppException
     */
    @Override
    public void deleteRunUserBalance(Integer id) throws AppException {
        RunUserBalance balance = runUserBalanceMapper.selectByPrimaryKey(id);
        if (balance == null) {
            throw new AppException(ResultEnum.QUERY_ERROR);
        }
        runUserBalanceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据用户id获取用户余额
     *
     * @param uid
     * @return
     * @throws AppException
     */
    @Override
    public RunUserBalance getRunUserBalanceByUID(Integer uid) throws AppException {
        RunUserBalance userBalance = runUserBalanceMapper.selectByPrimaryKey(uid);
        if (userBalance == null) {
            throw new AppException(ResultEnum.QUERY_ERROR.getCode(), ResultEnum.QUERY_ERROR.getMsg());
        }
        return userBalance;
    }
}
