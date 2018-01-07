package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.util.RegexUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liumingyu
 * @create 2018-01-07 下午8:56
 */
@RestController
@RequestMapping("/delivery")
public class RunDeliveryController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(RunDeliveryController.class);
    private static final String LOG_PREFIX = "【配送员模块】 ";

    @Autowired
    private RunDeliveryuserService runDeliveryuserService;

    @Autowired
    private RunDeliveryBalanceService runDeliveryBalanceService;

    @Autowired
    private RunDeliveryAddressService runDeliveryAddressService;

    @Autowired
    private RunDeliveryBalanceRecordService runDeliveryBalanceRecordService;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunDeliveryDistanceService runDeliveryDistanceService;

    /**
     * 验证账号是否可用
     *
     * @param phone 用户名
     * @return
     */
    @RequestMapping(value = "/check/{phone}", method = RequestMethod.GET)
    @ApiOperation(value = "验证账号是否可用(刘明宇)", notes = "验证账号是否可用", response = Object.class)
    public Object checkData(@PathVariable String phone) throws Exception {
        LOGGER.info("验证账号是否可用：" + phone);
        boolean flag;
        try {
            if (StringUtils.isBlank(phone)) {
                return BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
            }
            flag = runDeliveryuserService.checkRunDeliveryuser(phone);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "验证失败 phone = {} error = {}" + new Object[]{phone, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (flag) {
            return BaseResult.success(flag);
        } else {
            return BaseResult.fail(ResultEnum.TELTPHONE_DELIVERY);
        }
    }

    /**
     * 添加配送员
     *
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "添加配送员(刘明宇)", notes = "添加配送员", response = BaseResult.class)
    public BaseResult register(@RequestBody RunDeliveryuser user) throws Exception {
        LOGGER.info("注册用户，账号为：" + user.getUserphone());
        BaseResult result = null;
        if (user == null) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (!RegexUtils.checkMobile(user.getUserphone())) {
            return BaseResult.fail(ResultEnum.DELIVERY_PHONE_REGEX_IS_ERROR);
        }
        if (user.getUserphone() == null || "".equals(user.getUserphone().trim())
                || user.getUserphone() == null || "".equals(user.getUserphone().trim())) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 18) {
            return BaseResult.fail(ResultEnum.DELIVERY_PASSWORD_LEN);
        }
        try {
            runDeliveryuserService.insertRunDeliveryuser(user);
        } catch (AppException ae) {
            LOGGER.error("{} 注册配送员失败 user = {}", LOG_PREFIX, user);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(ResultEnum.DELIVERY_REGISTER_SUCCESS);
    }
}
