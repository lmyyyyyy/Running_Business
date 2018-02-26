package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.enums.RefundApplyStatusEnum;
import com.running.business.enums.UserTypeEnum;
import com.running.business.exception.AppException;
import com.running.business.facade.Cashier;
import com.running.business.pojo.RefundApply;
import com.running.business.pojo.ReportRecord;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RefundApplyService;
import com.running.business.service.RefundRecordService;
import com.running.business.service.ReportRecordService;
import com.running.business.service.RunUserAddressService;
import com.running.business.service.RunUserBalanceRecordService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserCouponService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserPreferenceService;
import com.running.business.service.RunUserService;
import com.running.business.util.JsonUtils;
import com.running.business.util.RequestUtil;
import com.running.business.vo.RefundRecordVO;
import com.running.business.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/users")
@Api(value = "用户账号模块", tags = {"用户账号模块"})
public class RunUserController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RunUserController.class);

    private static final String LOG_PREFIX = "【用户账号模块】 ";

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunUserAddressService runUserAddressService;

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private ReportRecordService reportRecordService;

    @Autowired
    private RefundApplyService refundApplyService;

    @Autowired
    private RefundRecordService refundRecordService;

    @Autowired
    private RunUserBalanceRecordService runUserBalanceRecordService;

    @Autowired
    private RunUserPreferenceService runUserPreferenceService;

    @Autowired
    private RunUserCouponService runUserCouponService;

    /**
     * 根据token获取用户信息
     *
     * @param token    token字符串
     * @param callback 回调
     * @return
     */
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据token获取用户信息(刘明宇)", notes = "根据token获取用户信息", response = BaseResult.class)
    public Object getUserByToken(@PathVariable String token, String callback) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        logger.info("根据token获取用户信息：", token);
        BaseResult result = null;
        try {
            result = runUserService.getUserByToken(token);
        } catch (AppException e) {
            logger.error(LOG_PREFIX + "获取失败 token = {}, error = {}", new Object[]{token, e});
            return BaseResult.fail(e.getErrorCode(), e.getMessage());
        }
        //判断是否为jsonp调用
        if (StringUtils.isBlank(callback)) {
            return result;
        } else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }

    /**
     * 用户注销
     *
     * @param token    token字符串
     * @param response 响应
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "用户注销(刘明宇)", notes = "用户注销", response = BaseResult.class)
    public BaseResult logout(@PathVariable String token, HttpServletResponse response) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        logger.info("用户注销", token);
        String callback = "http://localhost:8080";
        BaseResult result;
        try {
            result = runUserService.logout(token);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "用户注销失败 token = {}, error = {}", new Object[]{token, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
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
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        logger.info("异步检查旧密码是否匹配", oldPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunUser user = JsonUtils.jsonToPojo(jsonStr, RunUser.class);
        boolean flag;
        try {
            flag = runUserService.checkPwd(user.getUserphone(), oldPassword);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "check old pwd is error. username = {} oldpwd = {}, error = {}", new Object[]{user.getUserphone(), oldPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (flag) {
            return BaseResult.success();
        } else {
            return BaseResult.fail();
        }
    }

    /**
     * 修改用户密码
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatepwd", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "修改用户密码(刘明宇)", notes = "修改用户密码", response = BaseResult.class)
    public BaseResult modifyPwd(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword,
                                HttpServletRequest request) throws Exception {
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_OLD_AND_NEW_IS_SAME);
        }
        logger.info("修改用户密码", newPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunUser user = JsonUtils.jsonToPojo(jsonStr, RunUser.class);
        boolean flag;
        BaseResult result = null;
        try {
            flag = runUserService.checkPwd(user.getUserphone(), oldPassword);
            if (!flag) {
                return BaseResult.fail(ResultEnum.PWD_ERROR);
            }
            user.setPassword(newPassword);
            result = runUserService.updateUser(user);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "update pwd is error username = {}, newPassword = {}, error = {}", new Object[]{user.getUserphone(), newPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息(刘明宇)", notes = "获取用户信息", response = BaseResult.class)
    public BaseResult getUserInfo(HttpServletRequest request) throws Exception {
        logger.info("获取用户基本信息");
        UserVO userVO;
        Integer uid = null;
        try {
            uid = requestUtil.getUserId(request);
            userVO = runUserInfoService.getUserVOById(uid);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "获取用户基本信息失败 uid = {}, error = {}", new Object[]{uid, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(userVO);
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "更新用户信息(刘明宇)", notes = "更新用户信息", response = BaseResult.class)
    public BaseResult updateUserInfo(@RequestBody RunUserInfo userInfo, HttpServletRequest request) throws Exception {
        logger.info("更新用户信息", userInfo.getUserPhone());
        Integer uid = null;
        try {
            if (userInfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            uid = requestUtil.getUserId(request);
            userInfo.setUid(uid);
            runUserInfoService.updateRunUserInfo(userInfo);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "更新用户信息失败 uid = {}, error = {}", new Object[]{uid, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 获取用户所有的地址信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/address", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取用户所有的地址信息(刘明宇)", notes = "获取用户所有的地址信息", response = BaseResult.class)
    public BaseResult getAllUserAddr(HttpServletRequest request) throws Exception {
        logger.info("获取该用户的所有地址信息");
        Integer uid = null;
        BaseResult result;
        try {
            uid = requestUtil.getUserId(request);
            result = runUserAddressService.getAllRunUserAddress(uid);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "获取所有地址信息失败 uid = {}, error = {}", new Object[]{uid, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 根据id获取当前地址信息
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据id获取当前地址信息(刘明宇)", notes = "根据id获取当前地址信息", response = BaseResult.class)
    public BaseResult getUserAddrById(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
        logger.info("根据id获取当前地址信息", id);
        BaseResult result;
        try {
            result = runUserAddressService.getRunUserAddressByID(id);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "获取当前地址信息失败 id = {}, error = {}", new Object[]{id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 用户投诉配送员
     *
     * @param reportRecord
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/report", method = RequestMethod.POST)
    @ApiOperation(value = "用户投诉配送员(刘明宇)", notes = "用户投诉配送员", response = BaseResult.class)
    public BaseResult saveReportRecord(@RequestBody ReportRecord reportRecord, HttpServletRequest request) throws AppException {
        if (reportRecord == null) {
            return BaseResult.fail(ResultEnum.REPORT_INFO_IS_EMPTY);
        }
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 用户投诉配送员 uid = {}, reportrecord = {}", LOG_PREFIX, uid, reportRecord);
        try {
            reportRecord.setUid(uid);
            reportRecord.setActiveSide(UserTypeEnum.USER.getCode());
            reportRecordService.saveReportRecord(reportRecord);
        } catch (AppException ae) {
            logger.error("{} 用户投诉配送员失败 uid = {}, reportrecord = {}", LOG_PREFIX, uid, reportRecord);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 用户退款申请
     *
     * @param apply
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    @ApiOperation(value = "用户退款申请(刘明宇)", notes = "用户退款申请", response = BaseResult.class)
    public BaseResult refundApply(@RequestBody RefundApply apply, HttpServletRequest request) throws AppException {
        if (apply == null) {
            return BaseResult.fail(ResultEnum.REFUND_INFO_IS_EMTPY);
        }
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 用户退款申请 uid = {}", LOG_PREFIX, uid);
        try {
            apply.setUid(uid);
            refundApplyService.saveRefundApply(apply);
        } catch (AppException ae) {
            logger.error("{} 用户退款申请失败 uid = {}, error = {}", uid, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 取消退款申请
     *
     * @param orderId
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund/cancel", method = RequestMethod.PUT)
    @ApiOperation(value = "取消退款申请(刘明宇)", notes = "取消退款申请", response = BaseResult.class)
    public BaseResult cancelRefundApply(@RequestParam(value = "id", required = true) Integer id,
                                        @RequestParam(value = "orderId", required = true) String orderId,
                                        HttpServletRequest request) throws AppException {
        logger.info("{} 取消退款申请", LOG_PREFIX);
        try {
            RefundApply apply = new RefundApply();
            apply.setOrderid(orderId);
            apply.setId(id);
            apply.setStatus(RefundApplyStatusEnum.REFUND_CANCEL.getCode());
            refundApplyService.updateRefundApply(apply);
        } catch (AppException ae) {
            logger.error("{} 取消退款申请失败 error = {}", LOG_PREFIX, ae);
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
    @RequestMapping(value = "/fefunds", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取退款记录(刘明宇)", notes = "分页获取退款记录", response = BaseResult.class)
    public BaseResult pageRefundRecord(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                       @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                       HttpServletRequest request) throws AppException {
        logger.info("{} 分页获取退款记录 page = {}, size = {}", LOG_PREFIX, page, size);
        Integer uid = requestUtil.getUserId(request);
        PageInfo<RefundRecordVO> pageInfo;
        try {
            pageInfo = refundRecordService.pageRefundByUID(uid, page, size, orderField, orderType);
        } catch (AppException ae) {
            logger.error("{} 分页获取退款记录失败 uid = {}, page = {}, size = {}, error = {}", LOG_PREFIX, uid, page, size, ae);
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
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 查询余额 uid = {}", LOG_PREFIX, uid);
        return BaseResult.success(runUserBalanceService.getRunUserBalanceByUID(uid));
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
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 分页获取交易记录 uid = {}", LOG_PREFIX, uid);
        return BaseResult.success(runUserBalanceRecordService.pageAllRunUserBalanceRecordByUID(uid, page, size, orderField, orderType));
    }

    /**
     * 获取所有偏好
     *
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "获取所有偏好(刘明宇)", notes = "获取所有偏好", response = BaseResult.class)
    @RequestMapping(value = "/preferences", method = RequestMethod.GET)
    public BaseResult queryAllPreference(HttpServletRequest request) throws AppException {
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 获取所有偏好 uid = {}", LOG_PREFIX, uid);
        return BaseResult.success(runUserPreferenceService.getAllUserPreferenceByUID(uid));
    }

    /**
     * 分页获取优惠券
     *
     * @param page
     * @param size
     * @param status
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页获取优惠券(刘明宇)", notes = "分页获取优惠券", response = BaseResult.class)
    @RequestMapping(value = "/coupons", method = RequestMethod.GET)
    public BaseResult pageCoupons(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                  @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                  @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                  @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                  HttpServletRequest request) throws AppException {
        Integer uid = requestUtil.getUserId(request);
        logger.info("{} 分页获取优惠券 uid = {}", LOG_PREFIX, uid);
        return BaseResult.success(runUserCouponService.pageRunUserCouponByUID(uid, status, page, size, orderField, orderType));
    }

}
