package com.running.business.service;

import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;

import java.util.Map;

public interface HelpQueueOrderService {

    /**
     * 代排队业务-下订单
     *
     * @param order
     * @param uid
     * @return
     */
    Map<String, Object> saveHelpQueueOrder(RunOrder order, Integer uid) throws AppException;
}
