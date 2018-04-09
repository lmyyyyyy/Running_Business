package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.ResultEnum;
import com.running.business.enums.OrderTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RefundApply;
import com.running.business.pojo.RunOrder;
import com.running.business.pojo.RunOrderPay;
import com.running.business.sdk.BizFetcher;
import com.running.business.sdk.OrderServiceRegistry;
import com.running.business.sdk.OrderServiceStrategy;
import com.running.business.service.RefundApplyService;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserService;
import com.running.business.util.RequestUtil;
import com.running.business.vo.OrderVO;
import com.running.business.vo.RefundApplyVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    RefundApplyService refundApplyService;

    /**
     * 用户下单
     *
     * @param bizId
     * @param order
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/{bizId}", method = RequestMethod.POST)
    @ApiOperation(value = "用户下单(刘明宇)", notes = "用户下单", response = BaseResult.class)
    public BaseResult order(@PathVariable Integer bizId, @RequestBody RunOrder order, HttpServletRequest request) throws Exception {
        Integer uid = requestUtil.getUserId(request);
        if (uid == null) {
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
     * 用户支付
     *
     * @param orderPay
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "用户支付(刘明宇)", notes = "用户支付", response = BaseResult.class)
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public BaseResult pay(@RequestBody RunOrderPay orderPay, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 用户支付", LOG_PREFIX);
        if (orderPay == null) {
            throw new AppException(ResultEnum.ORDER_PAY_INFO_EMPTY);
        }
        Integer uid = requestUtil.getUserId(request);
        orderPay.setUid(uid);
        runOrderService.pay(orderPay, request);
        return BaseResult.success();
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     * @param status
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/status", method = RequestMethod.PUT)
    @ApiOperation(value = "修改订单状态(刘明宇)", notes = "修改订单状态", response = BaseResult.class)
    public BaseResult updateOStatus(@PathVariable("orderId") String orderId,
                                    @RequestParam(value = "status", required = true) Integer status,
                                    HttpServletRequest request) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return BaseResult.fail(ResultEnum.ORDER_ID_IS_ERROR);
        }
        if (status == null || status < 0) {
            return BaseResult.fail(ResultEnum.ORDER_STATUS_IS_ERROR);
        }
        LOGGER.info("{} 修改订单状态 orderId = {}, status = {}");
        runOrderService.updateOrderStatus(orderId, status);
        return BaseResult.success();
    }


    /**
     * 根据订单ID获取订单信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据订单ID获取订单信息(孙晓东)", notes = "根据订单ID获取订单信息", response = BaseResult.class)
    public BaseResult queryOrderInfoByOID(@PathVariable String id) {
        OrderVO orderVO;
        try {
            orderVO = runOrderService.getRunOrderByOID(id);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX + "，getOrderInfoByID方法异常：入参：{id:" + id + "},异常信息：{}", e);
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return BaseResult.success(orderVO);
    }

    /**
     * 模糊分页 根据关键字查询可抢订单(配送员刷单)
     *
     * @param keyword
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiOperation(value = "根据关键字查询可抢订单(刘明宇)", notes = "根据关键字查询可抢订单", response = BaseResult.class)
    public BaseResult pageOrders(@RequestParam(value = "type", required = false, defaultValue = "-1") Integer type,
                                 @RequestParam(value = "longitude", required = true) Double longitude,
                                 @RequestParam(value = "latitude", required = true) Double latitude,
                                 @RequestParam(value = "good", required = false, defaultValue = "") String good,
                                 @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                 HttpServletRequest request) throws AppException {
        Integer did;
        try {
            did = requestUtil.getDeliveryId(request);
        } catch (Exception e) {
            LOGGER.warn("{} 刷新可抢订单 当前未登录", LOG_PREFIX);
            did = null;
        }
        LOGGER.info("{} 根据关键字查询可抢订单 当前位置经度:[{}] 纬度:[{}], did = {}, good = {}, keyword = {}, page = {}, size = {}", LOG_PREFIX, longitude, latitude, did, good, keyword, page, size);
        PageInfo<OrderVO> pageInfo = runOrderService.pageRunOrderByPaid(type, did, longitude, latitude, good, keyword, page, size, orderType);
        return BaseResult.success(pageInfo);
    }

    /**
     * 模糊分页 根据状态查询历史订单(配送员自己操作)
     *
     * @param keyword
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/delivery", method = RequestMethod.GET)
    @ApiOperation(value = "根据配送状态模糊查询历史订单(刘明宇)", notes = "根据配送员ID和配送状态模糊查询订单", response = BaseResult.class)
    public BaseResult pageOrdersByKeywordAndStatus(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                                   @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                                   @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                                   @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                                   @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                                   HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 根据配送状态模糊查询历史订单 did = {}, status = {}, keyword = {}, page = {}, size = {}", LOG_PREFIX, did, status, keyword, page, size);
        PageInfo<OrderVO> pageInfo = runOrderService.pageRunOrderByDIDAndStatus(did, status, keyword, page, size, orderType);
        return BaseResult.success(pageInfo);
    }

    /**
     * 模糊分页 根据配送员id和状态查询订单(管理员操作)
     *
     * @param keyword
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/delivery/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据配送员ID和配送状态模糊查询订单(刘明宇)", notes = "根据配送员ID和配送状态模糊查询订单", response = BaseResult.class)
    public BaseResult pageOrdersByDidAndStatus(@PathVariable(value = "id") Integer id,
                                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                               HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据配送员ID和配送状态模糊查询订单 did = {}, status = {}, keyword = {}, page = {}, size = {}", LOG_PREFIX, id, status, keyword, page, size);
        PageInfo<OrderVO> pageInfo = runOrderService.pageRunOrderByDIDAndStatus(id, status, keyword, page, size, orderType);
        return BaseResult.success(pageInfo);
    }

    /**
     * 根据用户id和订单状态查询用户各种状态订单（分页）(用户自己操作)
     *
     * @param status 订单状态
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id和订单状态查询用户各种状态订单/分页(用户自己操作)(孙晓东)", notes = "根据用户id和订单状态查询用户各种状态订单（分页）", response = BaseResult.class)
    public BaseResult pageOrdersByKeyAndStatus(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                               HttpServletRequest request) {
        Integer id = requestUtil.getUserId(request);
        PageInfo<OrderVO> pageInfo = runOrderService.pageOrderByUIDAndStatus(keyword, id, status, page, size, orderType);
        return BaseResult.success(pageInfo);
    }




    //-----------------------------------退款begin--------------------------

    /**
     * 创建退款申请
     *
     * @param apply
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ApiOperation(value = "创建退款申请(刘明宇)", notes = "创建退款申请", response = BaseResult.class)
    public BaseResult saveRefundApply(@RequestBody RefundApply apply, HttpServletRequest request) throws AppException {
        if (apply == null) {
            return BaseResult.fail(ResultEnum.REFUND_INFO_IS_EMTPY);
        }
        Integer uid = requestUtil.getUserId(request);
        LOGGER.info("{} 创建退款申请 uid = {}, apply = {}", LOG_PREFIX, uid, apply);
        try {
            apply.setUid(uid);
            refundApplyService.saveRefundApply(apply);
        } catch (AppException ae) {
            LOGGER.error("{} 创建退款申请失败 uid = {}, apply = {}, error = {}", LOG_PREFIX, uid, apply, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 根据订单号查询退款信息（用户查询）
     *
     * @param orderId
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund", method = RequestMethod.GET)
    @ApiOperation(value = "根据订单号查询退款信息（用户查询）(刘明宇)", notes = "根据订单号查询退款信息", response = BaseResult.class)
    public BaseResult queryRefundByOrderId(@RequestParam(value = "orderId", required = true) String orderId, HttpServletRequest request) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return BaseResult.fail(ResultEnum.REFUND_ORDERID_IS_ERROR);
        }
        Integer uid = requestUtil.getUserId(request);
        LOGGER.info("{} 根据订单号查询退款信息 orderId = {}, uid = {}", LOG_PREFIX, orderId, uid);
        RefundApplyVO vo;
        try {
            vo = refundApplyService.queryApplyByOrderID(orderId, uid);
        } catch (AppException ae) {
            LOGGER.error("{} 根据订单号查询退款信息失败 uid = {}, orderId = {}, error = {}", LOG_PREFIX, uid, orderId, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(vo);
    }

    /**
     * 根据订单状态分页查询退款申请（用户查询）
     *
     * @param status
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refunds", method = RequestMethod.GET)
    @ApiOperation(value = "根据订单状态分页查询退款申请（用户查询）(刘明宇)", notes = "根据订单状态分页查询退款申请", response = BaseResult.class)
    public BaseResult pageRefundApply(@RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                      @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                      @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                      HttpServletRequest request) throws AppException {
        Integer uid = requestUtil.getUserId(request);
        LOGGER.info("{} 根据订单状态分页查询退款申请 uid = {}", LOG_PREFIX, uid);
        PageInfo<RefundApplyVO> applyVOs;
        try {
            applyVOs = refundApplyService.pageApplysByUID(uid, status, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 根据订单状态分页查询退款申请失败 uid = {}, error = {}", LOG_PREFIX, uid, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(applyVOs);
    }


    //-----------------------------------退款end--------------------------


}
