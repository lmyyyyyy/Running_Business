package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;

import java.util.Map;

public interface HelpSendOrderService {

    /**
     * 帮送业务-下订单
     *
     * @param order
     * @param uid
     * @return
     */
    Map<String, Object> saveHelpSendOrder(RunOrder order, Integer uid) throws AppException;
}
