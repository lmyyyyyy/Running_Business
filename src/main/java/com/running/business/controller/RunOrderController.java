package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.exception.AppException;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunxiaodong3 on 2018/1/7.
 */
@RestController
@RequestMapping("/order")
public class RunOrderController extends BaseController {
    private static Logger LOGGER = LoggerFactory.getLogger(RunOrderController.class);

    private static final String LOG_PREFIX = "【订单模块】 ";

    @Autowired
    RunUserService runUserService;

    @Autowired
    RunOrderService runOrderService;

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
