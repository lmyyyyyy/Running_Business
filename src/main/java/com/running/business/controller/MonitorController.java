package com.running.business.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumingyu
 * @create 2018-01-25 下午4:03
 */
@RestController
@RequestMapping("/monitor")
@Api(value = "监控平台模块", tags = {"监控平台模块"})
public class MonitorController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

    private static final String LOG_PREFIX = "【监控平台模块】 ";


}
