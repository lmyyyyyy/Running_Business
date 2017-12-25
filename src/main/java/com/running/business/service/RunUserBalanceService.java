package com.running.business.service;

import com.running.business.common.BaseResult;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserBalance;

public interface RunUserBalanceService {

    BaseResult addRunUserBalance(RunUserBalance runUserBalance) throws AppException;

    BaseResult updateRunUserBalance(RunUserBalance runUserBalance) throws AppException;

    BaseResult delRunUserBalance(Integer id) throws AppException;

    BaseResult getRunUserBalanceByUID(Integer uid) throws AppException;

}
