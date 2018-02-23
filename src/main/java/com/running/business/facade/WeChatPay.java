package com.running.business.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liumingyu
 * @create 2018-02-23 下午5:15
 */
public class WeChatPay {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeChatPay.class);

    public void weChatPay(double money, HttpServletRequest request) {
        LOGGER.debug("#####正在使用微信 支付 : ¥" + money);
        this.findWeixinApp();
        this.jumpToWeixin();
        this.authentication();
        this.getPayResult(money);
    }

    public void findWeixinApp () {
        System.out.println("查找是否安装微信APP");
    }

    public void jumpToWeixin () {
        System.out.println("跳转到微信");
    }

    public void authentication() {
        LOGGER.debug("身份认证");
    }

    public void getPayResult(double money) {
        LOGGER.debug("已支付：¥" + money);
    }
}
