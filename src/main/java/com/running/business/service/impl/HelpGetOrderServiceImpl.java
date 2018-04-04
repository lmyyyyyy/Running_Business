package com.running.business.service.impl;

import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.RunOrderMapper;
import com.running.business.pojo.RunOrder;
import com.running.business.sdk.OrderServiceStrategy;
import com.running.business.service.HelpGetOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("helpGetOrderService")
public class HelpGetOrderServiceImpl implements HelpGetOrderService, OrderServiceStrategy {

    @Autowired
    private RunOrderMapper runOrderMapper;

    @Override
    public Map<String, Object> saveHelpGetOrder(RunOrder order, Integer uid) throws AppException {
        return null;
    }

    @Override
    public Integer returnBizId() {
        return OrderTypeEnum.HELP_GET.getCode();
    }
}
