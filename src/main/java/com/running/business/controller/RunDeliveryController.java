package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.ReportRecord;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunOrder;
import com.running.business.service.RefundRecordService;
import com.running.business.service.ReportRecordService;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.service.RunOrderService;
import com.running.business.util.IdcardValidator;
import com.running.business.util.JsonUtils;
import com.running.business.util.RequestUtil;
import com.running.business.vo.DeliveryVO;
import com.running.business.vo.RefundRecordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author liumingyu
 * @create 2018-01-07 下午8:56
 */
@RestController
@RequestMapping("/delivery")
@Api(value = "配送员模块", tags = {"配送员模块"})
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

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private ReportRecordService reportRecordService;

    @Autowired
    private RefundRecordService refundRecordService;

    @Autowired
    private RunOrderService runOrderService;

    /**
     * 验证配送员身份证号格式
     *
     * @param id
     * @param card
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/check/card/id", method = RequestMethod.GET)
    @ApiOperation(value = "验证配送员身份证号格式(刘明宇)", notes = "验证配送员身份证号格式", response = BaseResult.class)
    public BaseResult checkCard(@PathVariable Integer id, String card, HttpServletRequest request) throws Exception {
        if (card == null || "".equals(card)) {
            LOGGER.error("{} 身份证号不能为空 id = {}", LOG_PREFIX, id);
            return BaseResult.fail(ResultEnum.DELIVERY_CARD_REGEX_IS_NOT_PASS);
        }
        LOGGER.info("{} 验证配送员身份证号格式 id = {}, card = {}", LOG_PREFIX, id, card);
        boolean flag;
        try {
            IdcardValidator iv = new IdcardValidator();
            flag = iv.isValidatedAllIdcard(card);
        } catch (AppException ae) {
            LOGGER.error("{} 身份证号验证异常 id = {}, card = {}", LOG_PREFIX, id, card);
            return BaseResult.fail(ResultEnum.DELIVERY_CARD_CHECK_ERROR);
        }
        if (flag) {
            return BaseResult.success();
        } else {
            return BaseResult.fail(ResultEnum.DELIVERY_CARD_REGEX_IS_NOT_PASS);
        }
    }


    /**
     * 根据token获取配送员信息
     *
     * @param token token字符串
     * @return
     */
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据token获取配送员信息(刘明宇)", notes = "根据token获取配送员信息", response = BaseResult.class)
    public Object getUserByToken(@PathVariable String token) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("根据token获取用户信息：", token);
        BaseResult result;
        try {
            result = runDeliveryuserService.getDeliveryuserByToken(token);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX + "获取失败 token = {}, error = {}", new Object[]{token, e});
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        return result;
    }

    /**
     * 配送员注销
     *
     * @param token    token字符串
     * @param response 响应
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "配送员注销(刘明宇)", notes = "配送员注销", response = BaseResult.class)
    public BaseResult logout(@PathVariable String token, HttpServletResponse response) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("用户注销", token);
        String callback = "http://localhost:8080";
        BaseResult result;
        try {
            result = runDeliveryuserService.logout(token);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "用户注销失败 token = {}, error = {}", new Object[]{token, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            response.sendRedirect(callback);
            return null;
        }
    }

    /**
     * 异步检查旧密码是否匹配
     *
     * @param oldPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkoldpwd", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "检查旧密码是否匹配(刘明宇)", notes = "检查旧密码是否匹配", response = BaseResult.class)
    public BaseResult checkOldPwd(@RequestParam("oldPassword") String oldPassword,
                                  HttpServletRequest request) throws Exception {
        if (oldPassword == null || "".equals(oldPassword)) {
            return BaseResult.fail(ResultEnum.DELIVERY_PASSWORD_LEN);
        }
        LOGGER.info("异步检查旧密码是否匹配", oldPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunDeliveryuser user = JsonUtils.jsonToPojo(jsonStr, RunDeliveryuser.class);
        boolean flag;
        try {
            flag = runDeliveryuserService.checkPhoneAndPwd(user.getUserphone(), oldPassword);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "check old pwd is error. username = {} oldpwd = {}, error = {}", new Object[]{user.getUserphone(), oldPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (flag) {
            return BaseResult.success();
        } else {
            return BaseResult.fail();
        }
    }

    /**
     * 修改配送员密码
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatepwd", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "修改配送员密码(刘明宇)", notes = "修改配送员密码", response = BaseResult.class)
    public BaseResult modifyPwd(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword,
                                HttpServletRequest request) throws Exception {
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.DELIVERY_PASSWORD_LEN);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.DELIVERY_OLD_AND_NEW_IS_SAME);
        }
        LOGGER.info("修改配送员密码", newPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunDeliveryuser user = JsonUtils.jsonToPojo(jsonStr, RunDeliveryuser.class);
        boolean flag;
        try {
            flag = runDeliveryuserService.checkPhoneAndPwd(user.getUserphone(), oldPassword);
            if (!flag) {
                return BaseResult.fail(ResultEnum.PWD_ERROR);
            }
            user.setPassword(newPassword);
            runDeliveryuserService.updateRunDeliveryuser(user);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "update pwd is error username = {}, newPassword = {}, error = {}", new Object[]{user.getUserphone(), newPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(user);
    }

    /**
     * 获取配送员信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取配送员信息(刘明宇)", notes = "获取配送员信息", response = BaseResult.class)
    public BaseResult getUserInfo(HttpServletRequest request) throws Exception {
        LOGGER.info("{} 获取配送员信息", LOG_PREFIX);
        DeliveryVO userVO;
        Integer did = null;
        try {
            String jsonStr = requestUtil.getToken(request);

            if (jsonStr == null) {
                return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
            }
            RunDeliveryuser user = JsonUtils.jsonToPojo(jsonStr, RunDeliveryuser.class);
            did = user.getDid();
            userVO = runDeliveryInfoService.getDeliveryVOByID(did);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "获取用户基本信息失败 did = {}, error = {}", new Object[]{did, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(userVO);
    }

    /**
     * 更新配送员信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "更新配送员信息(刘明宇)", notes = "更新配送员信息", response = BaseResult.class)
    public BaseResult updateUserInfo(@RequestBody RunDeliveryInfo userInfo, HttpServletRequest request) throws Exception {
        if (userInfo == null) {
            return BaseResult.fail(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        LOGGER.info("{} 更新配送员信息 phone = {}", LOG_PREFIX, userInfo.getPhone());
        Integer did = null;
        try {
            if (userInfo == null) {
                return BaseResult.fail(ResultEnum.DELIVERY_INFO_ISEMPTY);
            }
            did = requestUtil.getUserId(request);
            userInfo.setDid(did);
            runDeliveryInfoService.updateRunDeliveryInfo(userInfo);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "更新配送员信息 did = {}, error = {}", new Object[]{did, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 获取配送员所有的地址信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/address", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取配送员所有的地址信息(刘明宇)", notes = "获取配送员所有的地址信息", response = BaseResult.class)
    public BaseResult getAllUserAddr(HttpServletRequest request) throws Exception {
        LOGGER.info("获取配送员所有的地址信息");
        Integer did = null;
        List<RunDeliveryAddress> runDeliveryAddresses;
        try {
            did = requestUtil.getUserId(request);
            runDeliveryAddresses = runDeliveryAddressService.getAllRunDeliveryAddressByDID(did);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "获取配送员所有的地址信息失败 uid = {}, error = {}", new Object[]{did, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(runDeliveryAddresses);
    }

    /**
     * 根据id获取当前地址信息
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取当前地址信息(刘明宇)", notes = "根据id获取当前地址信息", response = BaseResult.class)
    public BaseResult getUserAddrById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        LOGGER.info("根据id获取当前地址信息", id);
        RunDeliveryAddress address;
        try {
            address = runDeliveryAddressService.getRunDeliveryAddressByID(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "获取当前地址信息失败 id = {}, error = {}", new Object[]{id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(address);
    }

    /**
     * 添加地址
     *
     * @param deliveryAddress
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ApiOperation(value = "添加地址(刘明宇)", notes = "添加地址", response = BaseResult.class)
    public BaseResult addAddr(@RequestBody RunDeliveryAddress deliveryAddress, HttpServletRequest request) throws AppException {
        if (deliveryAddress == null) {
            throw new AppException(ResultEnum.DELIVERY_ADDRESS_IS_EMPTY);
        }
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} {}添加地址{}", LOG_PREFIX, did, deliveryAddress.getDeliveryAddress());
        try {
            deliveryAddress.setDid(did);
            runDeliveryAddressService.saveRunDeliveryAddress(deliveryAddress);
        } catch (AppException ae) {
            LOGGER.error("{} 添加地址失败 operatorId = {}, address = {}", LOG_PREFIX, did, deliveryAddress);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 更新当前地址
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新当前地址(刘明宇)", notes = "更新当前地址", response = BaseResult.class)
    public BaseResult updateCurrAddr(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        if (id == null || id < 0) {
            throw new AppException(ResultEnum.INPUT_ERROR);
        }
        Integer did = requestUtil.getDeliveryId(request);
        try {
            RunDeliveryInfo info = new RunDeliveryInfo();
            info.setDid(did);
            info.setAddressId(id);
            runDeliveryInfoService.updateRunDeliveryInfo(info);
        } catch (AppException ae) {
            LOGGER.error("{} 更新当前地址失败 operatorId = {}, addressId = {}", LOG_PREFIX, did, id);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 查询配送距离
     *
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "查询配送距离(刘明宇)", notes = "查询配送距离", response = BaseResult.class)
    @RequestMapping(value = "/distance", method = RequestMethod.GET)
    public BaseResult queryDistance(HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 查询配送距离, did = {}", LOG_PREFIX, did);
        return BaseResult.success(runDeliveryDistanceService.getRunDeliveryDistanceByDID(did));
    }

    /**
     * 配送员抢单
     *
     * @param orderId
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "配送员抢单(刘明宇)", notes = "配送员抢单", response = BaseResult.class)
    @RequestMapping(value = "/grabOrder", method = RequestMethod.PUT)
    public BaseResult grabOrder(@RequestParam(value = "orderId", required = true) String orderId,
                                HttpServletRequest request) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return BaseResult.fail(ResultEnum.ORDER_ID_IS_ERROR);
        }
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 配送员抢单 did = {}, orderId = {}", LOG_PREFIX, did, orderId);
        runOrderService.updateOrderByGrab(orderId, did);
        return BaseResult.success();
    }

    /**
     * 配送员更新订单状态
     *
     * @param status
     * @param orderId
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "配送员更新订单状态(刘明宇)", notes = "配送员更新订单状态", response = BaseResult.class)
    @RequestMapping(value = "/modify/OrderStatus", method = RequestMethod.PUT)
    public BaseResult updateOrderStatus(@RequestParam(value = "status", required = true) Integer status,
                                        @RequestParam(value = "orderId", required = true) String orderId,
                                        HttpServletRequest request) throws AppException {
        if (orderId == null || "".equals(orderId)) {
            return BaseResult.fail(ResultEnum.ORDER_ID_IS_ERROR);
        }
        if (status == null || status < 0) {
            return BaseResult.fail(ResultEnum.ORDER_STATUS_IS_ERROR);
        }
        LOGGER.info("{} 配送员更新订单状态 orderId = {}, status = {}", LOG_PREFIX, orderId, status);
        runOrderService.updateOrderStatus(orderId, status);
        return BaseResult.success();
    }

    /**
     * 配送员更新配送距离
     *
     * @param deliveryDistance
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "配送员更新配送距离(刘明宇)", notes = "配送员更新配送距离", response = BaseResult.class)
    @RequestMapping(value = "/distance", method = RequestMethod.PUT)
    public BaseResult updateDistance(@RequestBody RunDeliveryDistance deliveryDistance, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 配送员更新配送距离", LOG_PREFIX);
        if (deliveryDistance == null) {
            return BaseResult.fail(ResultEnum.DELIVERY_DISTANCE_SET_ERROR);
        }
        Integer did = requestUtil.getDeliveryId(request);
        deliveryDistance.setDid(did);
        runDeliveryDistanceService.updateRunDeliveryDistance(deliveryDistance);
        return BaseResult.success();
    }

    /**
     * 配送员投诉用户
     *
     * @param reportRecord
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @ApiOperation(value = "配送员投诉用户(刘明宇)", notes = "配送员投诉用户", response = BaseResult.class)
    public BaseResult saveReportRecord(@RequestBody ReportRecord reportRecord, HttpServletRequest request) throws AppException {
        if (reportRecord == null) {
            return BaseResult.fail(ResultEnum.REPORT_INFO_IS_EMPTY);
        }
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 配送员投诉用户 did = {}, reportrecord = {}", LOG_PREFIX, did, reportRecord);
        try {
            reportRecord.setDid(did);
            reportRecord.setActiveSide(UserTypeEnum.DELIVERY.getCode());
            reportRecordService.saveReportRecord(reportRecord);
        } catch (AppException ae) {
            LOGGER.error("{} 配送员投诉用户失败 did = {}, reportrecord = {}", LOG_PREFIX, did, reportRecord);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 分页获取退款记录
     *
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refunds", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取退款记录(刘明宇)", notes = "分页获取退款记录", response = BaseResult.class)
    public BaseResult pageRefundRecord(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                       @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                       HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 分页获取退款记录", LOG_PREFIX);
        PageInfo<RefundRecordVO> pageInfo;
        try {
            pageInfo = refundRecordService.pageRefundByDID(did, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取退款记录失败 error = {}", LOG_PREFIX, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 查询余额
     *
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "查询余额(刘明宇)", notes = "查询余额", response = BaseResult.class)
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public BaseResult queryBalance(HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 查询余额 did = {}", LOG_PREFIX, did);
        return BaseResult.success(runDeliveryBalanceService.getRunDeliveryBalanceByDID(did));
    }

    /**
     * 分页获取交易记录
     *
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页获取交易记录(刘明宇)", notes = "分页获取交易记录", response = BaseResult.class)
    @RequestMapping(value = "/balances", method = RequestMethod.GET)
    public BaseResult pageBalanceRecords(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                         @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                         @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                         HttpServletRequest request) throws AppException {
        Integer did = requestUtil.getDeliveryId(request);
        LOGGER.info("{} 分页获取交易记录 did = {}", LOG_PREFIX, did);
        return BaseResult.success(runDeliveryBalanceRecordService.pageAllDeliveryRecordByDID(did, page, size, orderField, orderType));
    }

    /**
     * 上传配送员头像
     * @param file
     * @param request
     * @return
     */
    @ApiOperation(value = "上传配送员头像(孙晓东)", notes = "上传配送员头像", response = BaseResult.class)
    @RequestMapping(value = "/uploadDeliveryPhoto", method = RequestMethod.POST)
    public BaseResult uploadDeliveryPhoto(MultipartFile file, HttpServletRequest request) {
        BaseResult result = null;
        Integer did = requestUtil.getDeliveryId(request);
        try {
            result = runDeliveryInfoService.uploadDeliveryImg(file, did);
        } catch (AppException e) {
            LOGGER.error("{} ：配送员头像上传异常 did = {}", LOG_PREFIX, did, e);
        }
        return result;
    }

    /**
     * 上传配送员审核身份证
     * @param file
     * @param request
     * @return
     */
    @ApiOperation(value = "上传配送员头像(孙晓东)", notes = "上传配送员头像", response = BaseResult.class)
    @RequestMapping(value = "/uploadDeliveryPhoto", method = RequestMethod.POST)
    public BaseResult uploadDeliveryCard(MultipartFile file, HttpServletRequest request) {
        BaseResult result = null;
        Integer did = requestUtil.getDeliveryId(request);
        try {
            result = runDeliveryuserService.uploadDeliveryCard(file, did);
        } catch (AppException e) {
            LOGGER.error("{} ：配送员身份证图片上传异常 did = {}", LOG_PREFIX, did, e);
        }
        return result;
    }

}
