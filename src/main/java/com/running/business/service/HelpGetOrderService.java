package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;

import java.util.Map;

public interface HelpGetOrderService {

    /**
     * 帮取业务-下订单
     *
     * @param order
     * @param uid
     * @return
     */
    Map<String, Object> saveHelpGetOrder(RunOrder order, Integer uid) throws AppException;
}
