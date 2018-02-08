package com.running.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.service.RunUserCouponService;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import jdk.nashorn.api.scripting.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sunxiaodong3 on 2018/1/16.
 */
@RestController
@RequestMapping("/coupon")
public class RunCouponController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunCouponController.class);

    private static final String LOG_PREFIX = "【优惠券模块】 ";

    @Autowired
    private RunUserCouponService couponService;

    /**
     * 获取用户ID和优惠券状态查询优惠券(分页)
     * @param uid
     * @param status 是否可用（0：可用，1：不可用）
     * @return
     */
    @RequestMapping(value = "/queryCouponByUidAndStatus/{id}/{status}/{currentPage}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取用户ID和优惠券状态查询优惠券/分页(孙晓东)", notes = "获取用户ID和优惠券状态查询优惠券（分页）", response = BaseResult.class)
    public BaseResult getCouponByUidAndStatus(@PathVariable Integer uid, @PathVariable Integer status, @PathVariable Integer currentPage) {
        BaseResult result = null;
        try {
            if (currentPage == null) {
                currentPage = 1;
            }
            result = couponService.queryCouponByUidAndStatus(uid, status, currentPage);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX+"，getCouponByUid方法异常：入参：{id:{},status:{}, currentPage},异常信息：{}", uid, status, currentPage, e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据用户ID获取所有用户优惠券(分页)
     * @param uid 用户ID
     * @param currentPage 当前页
     * @return
     */
    @RequestMapping(value = "/queryCouponByUidAndStatus/{id}/{currentPage}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据用户ID获取所有用户优惠券/分页(孙晓东)", notes = "根据用户ID获取所有用户优惠券（分页）", response = BaseResult.class)
    public BaseResult getAllCouponByUid(@PathVariable Integer uid, @PathVariable Integer currentPage) {
        BaseResult result = null;
        try {
            if (currentPage == null) {
                currentPage = 1;
            }
            result = couponService.getAllRunUserCouponByUID(uid, currentPage);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX + "，getCouponByUid方法异常：入参：{id:{},currentPage:{}},异常信息：{}", uid, currentPage, e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
    }


    @RequestMapping(value = "/addCouponForUserByUid", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据用户ID获取所有用户优惠券/分页(孙晓东)", notes = "根据用户ID获取所有用户优惠券（分页）", response = BaseResult.class)
    public BaseResult addCouponForUserByUid(@RequestBody RunUserCoupon runUserCoupon) {
        BaseResult result = null;
        try {
            
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX + "，getCouponByUid方法异常：入参：{},异常信息：{}", JSONObject.toJSONString(runUserCoupon), e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
    }

}
