package com.running.business.facade;

import com.running.business.enums.PaySourceTypeEnum;
import com.running.business.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liumingyu
 * @create 2018-02-23 下午5:06
 */
@Component
public class Cashier {

    @Autowired
    private BalancePay balancePay;

    /**
     * 用户支付（暂只支持余额支付）
     *
     * @param channel
     * @param money
     * @param request
     * @throws AppException
     */
    public void pay(PaySourceTypeEnum channel, double money, HttpServletRequest request)  throws AppException {
        switch (channel) {
            case BALANCE:
                balancePay.balancePay(money, request);
                break;
            case BANK:
                BankPay bankPay = new BankPay();
                bankPay.bankPay(money, request);
                break;
            case ALIPAY:
                AliPay aliPay = new AliPay();
                aliPay.aliPay(money, request);
                break;
            case APPLEPAY:
                ApplePay applePay = new ApplePay();
                applePay.applePay(money, request);
                break;
            case WECHATPAY:
                WeChatPay weChatPay = new WeChatPay();
                weChatPay.weChatPay(money, request);
                break;
            default:
                break;
        }
    }
}
