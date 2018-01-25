package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;

import com.running.business.common.ResultEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunOrder;
import com.running.business.sdk.BizFetcher;
import com.running.business.sdk.OrderServiceRegistry;
import com.running.business.sdk.OrderServiceStrategy;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserService;
import com.running.business.util.RequestUtil;
import io.swagger.annotations.Api;
import com.running.business.exception.AppException;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by sunxiaodong3 on 2018/1/7.
 */
@RestController
@RequestMapping("/order")
@Api(value = "订单模块", tags = {"订单模块"})
public class RunOrderController extends BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(RunOrderController.class);

    private static final String LOG_PREFIX = "【订单模块】 ";

    @Autowired
    RunUserService runUserService;

    @Autowired
    RunOrderService runOrderService;


    @Autowired
    OrderServiceRegistry orderServiceRegistry;

    @Autowired
    RequestUtil requestUtil;


    @RequestMapping(value = "/{bizId}", method = RequestMethod.POST)
    @ApiOperation(value = "用户下单(刘明宇)", notes = "用户下单", response = BaseResult.class)
    public BaseResult order(@PathVariable Integer bizId, @RequestBody RunOrder order, HttpServletRequest request) throws Exception {
        Integer uid = requestUtil.getUserId(request);
        if (uid == null ){
            LOGGER.error("{} 用户session失效");
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("{} 用户{}下单 业务线为{}", LOG_PREFIX, uid, bizId);

        OrderTypeEnum orderTypeEnum = OrderTypeEnum.getOrderTypeEnum(bizId);
        if (orderTypeEnum == null) {
            LOGGER.error("{} 未知的订单类型, bizId = {}", LOG_PREFIX, bizId);
            return BaseResult.fail(ResultEnum.NOT_HAVE_THIS_BIZID);
        }
        OrderServiceStrategy orderServiceStrategy = orderServiceRegistry.getOrderServiceStrategy(bizId);
        if (orderServiceStrategy == null) {
            LOGGER.error("{} 没有该条业务线, bizId = {}", LOG_PREFIX, bizId);
            return BaseResult.fail(ResultEnum.NOT_HAVE_THIS_BIZID);
        }
        return BaseResult.success(BizFetcher.fetchMap(bizId, orderTypeEnum, order, uid));
    }

    /**
     * 根据订单ID获取订单信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryOrder/{id}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据订单ID获取订单信息(孙晓东)", notes = "根据订单ID获取订单信息", response = BaseResult.class)
    public BaseResult getOrderInfoByUserToken(@PathVariable String id) {
        BaseResult result = null;
        try {
            result = runOrderService.getRunOrderByOID(id);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX+"，getOrderInfoByUserToken方法异常：入参：{id:"+id+"},异常信息：{}", e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 根据用户id获取用户订单（分页）
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/queryAllOrderByUId/{id}/{currentPage}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据用户id获取用户订单/分页(孙晓东)", notes = "根据用户id获取用户订单（分页）", response = BaseResult.class)
    public BaseResult getAllOrderByUserId (@PathVariable int id, @PathVariable int currentPage) {
        BaseResult result = null;
        try {
            result = runOrderService.getAllRunOrderByUID(id);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX+"，getAllOrderByUserId方法异常：入参：{id:"+id+",currentPage:"+currentPage+"},异常信息：{}", e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
   }

    /**
     * 根据配送员ID获取订单信息（分页）
     * @param id
     * @param currentPage
     * @return
     */
   @RequestMapping(value = "/queryAllOrderByDId/{id}/{currentPage}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
   @ApiOperation(value = "根据配送员ID获取订单信息/分页(孙晓东)", notes = "根据配送员ID获取订单信息（分页）", response = BaseResult.class)
   public  BaseResult getAllOrderByDID(@PathVariable int id, @PathVariable int currentPage) {
        BaseResult result = null;
        try {
            result = runOrderService.getAllRunOrderByDID(id, currentPage);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX+"，getAllOrderByUserId方法异常：入参：{id:"+id+",currentPage:"+currentPage+"},异常信息：{}", e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
   }

    /**
     * 根据用户id和订单状态查询用户各种状态订单（分页）
     * @param id 用户id
     * @param currentPage 当前页数
     * @param status 订单状态
     * @return
     */
   @RequestMapping(value = "/queryOrderByStatus/{id}/{status}/{currentPage}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
   @ApiOperation(value = "根据用户id和订单状态查询用户各种状态订单/分页(孙晓东)", notes = "根据用户id和订单状态查询用户各种状态订单（分页）", response = BaseResult.class)
   public BaseResult getOrderByStatus(@PathVariable int id, @PathVariable int status, @PathVariable int currentPage) {
        BaseResult result = null;
        try {
            result = runOrderService.getOrderByUIDAndStatus(id, status, currentPage);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX+"，getAllOrderByUserId方法异常：入参：{id:"+id+",currentPage:"+currentPage+"},异常信息：{}", e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
   }



}
