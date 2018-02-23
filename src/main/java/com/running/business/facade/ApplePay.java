package com.running.business.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liumingyu
 * @create 2018-02-23 下午5:15
 */
public class ApplePay {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplePay.class);

    public void applePay(double money, HttpServletRequest request) {
        LOGGER.debug("#####正在使用苹果 支付 : ¥" + money);
        this.securityCheck();
        this.getBankCardList();
        this.checkCardInServer();
        this.authentication();
        this.getPayResult(money);
    }

    public void securityCheck() {
        LOGGER.debug("安全检查");
    }

    public void getBankCardList() {
        LOGGER.debug("获取银行卡列表");
    }

    public void checkCardInServer() {
        LOGGER.debug("检查银行卡可用性");
    }

    public void authentication() {
        LOGGER.debug("身份认证");
    }

    public void getPayResult(double money) {
        LOGGER.debug("已支付：¥" + money);
    }
}
