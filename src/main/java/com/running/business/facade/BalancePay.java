package com.running.business.facade;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserBalanceRecord;
import com.running.business.service.RunUserBalanceRecordService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.util.RandomUtil;
import com.running.business.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author liumingyu
 * @create 2018-02-23 下午5:16
 */
@Component
public class BalancePay {
    private static final Logger LOGGER = LoggerFactory.getLogger(BalancePay.class);

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserBalanceRecordService runUserBalanceRecordService;

    @Autowired
    private RequestUtil requestUtil;

    public synchronized void balancePay(double money, HttpServletRequest request) throws AppException {
        LOGGER.debug("#####正在使用余额 支付 : ¥" + money);
        this.securityCheck();
        this.getBankCardList();
        this.checkCardInServer();
        Integer uid = this.authentication(request);
        RunUserBalance balance = runUserBalanceService.getRunUserBalanceByUID(uid);
        if (balance.getUserBalance() < money) {
            throw new AppException(ResultEnum.USER_BALANCE_LESS);
        }
        RunUserBalanceRecord record = new RunUserBalanceRecord();
        record.setUid(uid);
        record.setOldBalance(balance.getUserBalance());
        balance.setUserBalance(balance.getUserBalance() - money);
        runUserBalanceService.updateRunUserBalance(balance);
        record.setNewBalance(balance.getUserBalance());
        record.setAmount(money);
        record.setAddTime(new Date());
        record.setType(true);
        runUserBalanceRecordService.saveRunUserBalanceRecord(record);
        this.stort2MyBankCard(money);
        this.getPayResult(money);
    }

    public void securityCheck() throws AppException {
        LOGGER.debug("安全检查");
    }

    public void getBankCardList() throws AppException {
        LOGGER.debug("获取银行卡列表");
    }

    public void checkCardInServer() throws AppException {
        LOGGER.debug("检查银行卡可用性");
    }

    public Integer authentication(HttpServletRequest request) throws AppException {
        LOGGER.debug("身份认证");
        return requestUtil.getUserId(request);
    }

    public void getPayResult(double money) throws AppException {
        LOGGER.debug("已支付：¥" + money);
    }

    public void stort2MyBankCard(double money) throws AppException {
        LOGGER.debug("存入我的银行卡");
    }

}
