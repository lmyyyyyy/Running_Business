package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumingyu
 * @create 2017-11-28 上午10:24
 */
@RestController
public class BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler(Throwable.class)
    public BaseResult HandleAllException(Throwable e) {
        if (e instanceof AppException) {
            return new BaseResult(((AppException) e).getErrorCode(), e.getMessage());
        } else {
            LOGGER.error("BaseController " + e.getMessage());
            return BaseResult.fail(ResultEnum.INNER_ERROR.getCode(), ResultEnum.INNER_ERROR.getMsg());
        }
    }
}