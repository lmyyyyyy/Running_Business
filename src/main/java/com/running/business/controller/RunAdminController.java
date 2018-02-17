package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.dto.RefundDTO;
import com.running.business.exception.AppException;
import com.running.business.interceptor.PermissionInterceptor;
import com.running.business.pojo.ReportRecord;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunUser;
import com.running.business.service.RefundApplyService;
import com.running.business.service.RefundRecordService;
import com.running.business.service.ReportRecordService;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
import com.running.business.service.RunUserAddressService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserCouponService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserPreferenceService;
import com.running.business.service.RunUserService;
import com.running.business.util.JsonUtils;
import com.running.business.util.RequestUtil;
import com.running.business.vo.AdminVO;
import com.running.business.vo.RefundApplyVO;
import com.running.business.vo.RefundRecordVO;
import com.running.business.vo.ReportRecordVO;
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

/**
 * @author liumingyu
 * @create 2017-12-24 下午3:09
 */
@RestController
@RequestMapping(value = "/admins")
@Api(value = "管理员模块", tags = {"管理员模块"})
public class RunAdminController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(RunAdminController.class);

    private static final String LOG_PREFIX = "【管理员模块】 ";

    @Autowired
    private RunAdminService runAdminService;

    @Autowired
    private RunAdminInfoService runAdminInfoService;

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunUserAddressService runUserAddressService;

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserCouponService runUserCouponService;

    @Autowired
    private RunUserPreferenceService runUserPreferenceService;

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private PermissionInterceptor permissionInterceptor;

    @Autowired
    private RefundApplyService refundApplyService;

    @Autowired
    private ReportRecordService reportRecordService;

    @Autowired
    private RefundRecordService refundRecordService;

    /**
     * 添加管理员
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     *//*
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "添加管理员(刘明宇)", notes = "添加管理员", response = BaseResult.class)
    public BaseResult insertAdmin(@RequestBody RunAdmin admin, HttpServletRequest request) throws Exception {
        if (admin.getAdminUsername() == null || admin.getAdminUsername().trim().equals("")
                || admin.getAdminPassword() == null || admin.getAdminPassword().trim().equals("")) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        *//*if (admin.getAdminPassword().length() < 6 || admin.getAdminPassword().length() > 18) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        if (!RegexUtils.checkMobile(admin.getAdminUsername())) {
            return BaseResult.fail(ResultEnum.USER_PHONE_REGEX_IS_NOT);
        }*//*
        //Integer adminId = requestUtil.getAdminId(request);
        Integer adminId = -1;
        LOGGER.info("{}添加管理员，账号为：{}", new Object[]{adminId, admin.getAdminUsername()});
        BaseResult result = null;
        try {
            result = runAdminService.checkAdmin(admin.getAdminUsername());
            if (!result.getCode().equals("200")) {
                return result;
            }
            result = runAdminService.saveRunAdmin(admin);
            if (result.getCode().equals("200")) {
                Integer id = admin.getAdminId();
                RunAdminInfo adminInfo = new RunAdminInfo();
                adminInfo.setAdminId(id);
                adminInfo.setAdminPhone(admin.getAdminUsername());
                String name = RandomUtil.generateRandomDigitString(5);
                int count = 0;
                while (!runAdminInfoService.checkNameUnique(name)) {
                    if (count >= 5) {
                        return BaseResult.fail(ResultEnum.Number_THAN_BIG);
                    }
                    name = RandomUtil.generateRandomDigitString(5);
                    count++;
                }
                adminInfo.setAdminName("管理员_" + name);
                runAdminInfoService.saveRunAdminInfo(adminInfo);
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "添加管理员失败 operationId = {},error = {}", new Object[]{adminId, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(admin);
    }

    /**
     * 管理员登录
     *
     * @param admin    用户实体
     * @param request  请求
     * @param response 响应
     * @return
     *//*
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "用户登录(刘明宇)", notes = "用户登录", response = BaseResult.class)
    public BaseResult login(@RequestBody RunAdmin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (admin.getAdminUsername() == null || admin.getAdminUsername().trim().equals("")
                || admin.getAdminPassword() == null || admin.getAdminPassword().trim().equals("")) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
        }
        LOGGER.info("管理员登录：" + admin.getAdminUsername());
        BaseResult result = null;
        try {
            result = runAdminService.login(admin.getAdminUsername(), admin.getAdminPassword(), request, response);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "登录失败- admin = {}, error = {}" + new Object[]{admin, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }
*/

    /**
     * 根据token获取管理员信息
     *
     * @param token    token字符串
     * @param callback 回调
     * @return
     */
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据token获取管理员信息(刘明宇)", notes = "根据token获取管理员信息", response = BaseResult.class)
    public Object getUserByToken(@PathVariable String token, String callback) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("根据token获取管理员信息：", token);
        BaseResult result = null;
        try {
            result = runAdminService.getAdminByToken(token);
        } catch (AppException e) {
            LOGGER.error(LOG_PREFIX + "获取失败 token = {}, error = {}", new Object[]{token, e});
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
     * 注销
     *
     * @param token    token字符串
     * @param response 响应
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "管理员注销(刘明宇)", notes = "管理员注销", response = BaseResult.class)
    public BaseResult logout(@PathVariable String token, HttpServletResponse response) throws Exception {
        if (token == null || "".equals(token)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("管理员注销", token);
        String callback = "http://localhost:8080";
        BaseResult result = null;
        try {
            result = runAdminService.logout(token);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "管理员注销失败 token = {}, error = {}", new Object[]{token, ae});
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
            return BaseResult.fail(ResultEnum.ADMIN_PASSWORD_REGEX_ERROR);
        }
        LOGGER.info("异步检查旧密码是否匹配", oldPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        boolean flag;
        try {
            flag = runAdminService.checkPwd(admin.getAdminUsername(), oldPassword);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "check old pwd is error. username = {} oldpwd = {}, error = {}", new Object[]{admin.getAdminUsername(), oldPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (flag) {
            return BaseResult.success();
        } else {
            return BaseResult.fail();
        }
    }

    /**
     * 修改管理员密码
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatepwd", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "修改管理员密码(刘明宇)", notes = "修改管理员密码", response = BaseResult.class)
    public BaseResult modifyPwd(@RequestParam("oldPassword") String oldPassword,
                                @RequestParam("newPassword") String newPassword,
                                HttpServletRequest request) throws Exception {
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.ADMIN_PASSWORD_REGEX_ERROR);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.ADMIN_PASSWORD_NEW_AND_OLD_IS_SAME);
        }
        LOGGER.info("修改管理员密码", newPassword);
        String jsonStr = requestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        boolean flag;
        try {
            flag = runAdminService.checkPwd(admin.getAdminUsername(), oldPassword);
            if (!flag) {
                return BaseResult.fail(ResultEnum.PWD_ERROR);
            }
            admin.setAdminPassword(newPassword);
            runAdminService.updateRunAdminPassword(admin);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "update pwd is error username = {}, newPassword = {}, error = {}", new Object[]{admin.getAdminUsername(), newPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 获取管理员信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取管理员信息(刘明宇)", notes = "获取管理员信息", response = BaseResult.class)
    public BaseResult getUserInfo(HttpServletRequest request) throws Exception {
        LOGGER.info("获取管理员基本信息");
        AdminVO adminVO;
        Integer aid = null;
        try {
            aid = requestUtil.getAdminId(request);
            adminVO = runAdminInfoService.getAdminVOById(aid);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "获取管理员基本信息失败 uid = {}, error = {}", new Object[]{aid, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(adminVO);
    }

    /**
     * 根据id获取管理员信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "获取管理员信息(刘明宇)", notes = "获取管理员信息", response = BaseResult.class)
    public BaseResult getAdminInfoById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        if (operator == null) {
            LOGGER.info("获取用户信息失败");
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        LOGGER.info("{} 根据id获取管理员基本信息 id = {}", new Object[]{operator, id});
        AdminVO adminVO;
        try {
            adminVO = runAdminInfoService.getAdminVOById(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "获取管理员基本信息失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(adminVO);
    }

    /**
     * 根据id获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "根据id获取用户信息(刘明宇)", notes = "根据id获取用户信息", response = BaseResult.class)
    public BaseResult getUserInfoById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 根据id获取用户信息 id = {}", new Object[]{operator, id});
        UserVO userVO;
        try {
            userVO = runUserInfoService.getUserVOById(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "根据id获取用户信息 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(userVO);
    }

    /**
     * 更新管理员信息
     *
     * @param userAdminfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "更新管理员信息(刘明宇)", notes = "更新管理员信息", response = BaseResult.class)
    public BaseResult updateUserInfo(@RequestBody RunAdminInfo userAdminfo, HttpServletRequest request) throws Exception {
        LOGGER.info("更新管理员信息", userAdminfo.getAdminPhone());
        Integer uid = null;
        BaseResult result;
        try {
            if (userAdminfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            uid = requestUtil.getAdminId(request);
            userAdminfo.setAdminId(uid);
            result = runAdminInfoService.updateRunAdminInfo(userAdminfo);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "更新管理员信息失败 uid = {}, error = {}", new Object[]{uid, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 根据id删除管理员
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "admin/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除管理员(刘明宇)", notes = "根据id删除管理员", response = BaseResult.class)
    public BaseResult delAdmin(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 删除管理员 id = {}", new Object[]{operator, id});
        BaseResult result;
        try {
            result = runAdminService.deleteRunAdminByID(id);
            if (result.getCode().equals("200")) {
                runAdminInfoService.deleteRunAdminInfoByID(id);
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "删除管理员失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除用户(刘明宇)", notes = "根据id删除用户", response = BaseResult.class)
    public BaseResult delUser(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 删除用户 id = {}", new Object[]{operator, id});
        BaseResult result;
        try {
            result = runUserService.deleteUser(id);
            if (result.getCode().equals("200")) {
                runUserInfoService.deleteRunUserInfo(id);
                runUserAddressService.deleteAllRunUserAddressByUID(id);
                runUserPreferenceService.deleteAllRunUserPreferenceByUID(id);
                runUserCouponService.deleteAllRunUserCoupon(id);
                runUserBalanceService.deleteRunUserBalance(id);
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "删除用户失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 分页获取所有未被删除的用户列表
     *
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有未被删除的用户列表(刘明宇)", notes = "分页获取所有未被删除的用户列表", response = BaseResult.class)
    public BaseResult pageUsers(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 分页获取所有未被删除的用户列表 operator = {}", new Object[]{LOG_PREFIX, operator});
        if (!permissionInterceptor.isInvoke(request)) {
            LOGGER.error("{} 当前用户没有权限进行操作 operator = {}", LOG_PREFIX, operator);
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        PageInfo<RunUser> pageInfo;
        try {
            pageInfo = runUserService.pageAllRunUser(page, size, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取用户列表失败 operator = {}, error = {}", LOG_PREFIX, operator, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }


    //-----------------------------------退款begin--------------------------


    /**
     * 根据订单状态分页查询退款申请 (管理员查询)
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
    @ApiOperation(value = "根据订单状态分页查询退款申请(管理员查询)(刘明宇)", notes = "根据订单状态分页查询退款申请", response = BaseResult.class)
    public BaseResult pageRefundApplys(@RequestParam(value = "onlyme", required = false, defaultValue = "false") boolean onlyme,
                                       @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                       @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                       HttpServletRequest request) throws AppException {
        Integer adminId = requestUtil.getAdminId(request);
        LOGGER.info("{} 根据订单状态分页查询退款申请 adminId = {}", LOG_PREFIX, adminId);
        PageInfo<RefundApplyVO> applyVOs;
        try {
            if (onlyme) {
                applyVOs = refundApplyService.pageApplysByOperatorId(adminId, page, size, orderField, orderType);
            } else {
                applyVOs = refundApplyService.pageApplys(status, page, size, orderField, orderType);
            }
        } catch (AppException ae) {
            LOGGER.error("{} 根据订单状态分页查询退款申请失败 adminId = {}, error = {}", LOG_PREFIX, adminId, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(applyVOs);
    }

    /**
     * 根据退款申请id获取退款信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据退款申请id获取退款信息(刘明宇)", notes = "根据退款申请id获取退款信息", response = BaseResult.class)
    public BaseResult queryRefundByID(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据退款申请id获取退款信息 id = {}", LOG_PREFIX, id);
        RefundApplyVO refundApplyVO = refundApplyService.queryApplyByID(id);
        return BaseResult.success(refundApplyVO);
    }

    /**
     * 更新退款申请状态（同意或拒绝）
     *
     * @param refundDTO
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund", method = RequestMethod.PUT)
    @ApiOperation(value = "更新退款申请状态（同意或拒绝）(刘明宇)", notes = "更新退款申请状态（同意或拒绝）", response = BaseResult.class)
    public BaseResult updateRefundStatus(@RequestBody RefundDTO refundDTO,
                                         HttpServletRequest request) throws AppException {
        Integer adminId = requestUtil.getAdminId(request);
        LOGGER.info("{} 更新退款申请状态（同意或拒绝） status = {}, id = {}, orderId = {}, adminId = {}", LOG_PREFIX, refundDTO.getStatus(), refundDTO.getId(), refundDTO.getOrderId(), adminId);
        try {
            refundApplyService.updateApplyStatus(refundDTO);
        } catch (AppException ae) {
            LOGGER.error("{} 更新退款申请状态（同意或拒绝）失败 status = {}, id = {}, orderId = {}, adminId = {}, error = {}", LOG_PREFIX, refundDTO.getStatus(), refundDTO.getId(), refundDTO.getOrderId(), adminId, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 根据id删除退款申请
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除退款申请(刘明宇)", notes = "根据id删除退款申请", response = BaseResult.class)
    public BaseResult deleteRefundByID(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据id删除退款申请 id = {}", LOG_PREFIX, id);
        refundApplyService.deleteRefundApplyByID(id);
        return BaseResult.success();
    }

    /**
     * 根据订单id删除退款申请
     *
     * @param orderId
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/refund/orderid", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据订单id删除退款申请(刘明宇)", notes = "根据订单id删除退款申请", response = BaseResult.class)
    public BaseResult deleteRefundByOrderId(@RequestParam(value = "orderId", required = true) String orderId, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据订单id删除退款申请 orderId = {}", LOG_PREFIX, orderId);
        refundApplyService.deleteRefundApplyByOrderid(orderId);
        return BaseResult.success();
    }

    /**
     * 根据用户id删除所有退款申请
     *
     * @param uid
     * @param request
     * @return
     */
    @RequestMapping(value = "/refund", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据用户id删除所有退款申请(刘明宇)", notes = "根据用户id删除所有退款申请", response = BaseResult.class)
    public BaseResult deleteRefundByUID(@RequestParam(value = "uid", required = true) Integer uid, HttpServletRequest request) {
        LOGGER.info("{} 根据用户id删除所有退款申请 uid = {}", LOG_PREFIX, uid);
        refundApplyService.deleteAllApplyByUID(uid);
        return BaseResult.success();
    }

    /**
     * 分页获取自己操作的退款记录
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
    @ApiOperation(value = "分页获取自己操作的退款记录(刘明宇)", notes = "分页获取自己操作的退款记录", response = BaseResult.class)
    public BaseResult pageRefundRecord(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                       @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                       HttpServletRequest request) throws AppException {
        Integer operatorId = requestUtil.getAdminId(request);
        LOGGER.info("{} 分页获取自己操作的退款记录", LOG_PREFIX);
        PageInfo<RefundRecordVO> pageInfo;
        try {
            pageInfo = refundRecordService.pageRefundByOperatorId(operatorId, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取自己操作的退款记录 error = {}", LOG_PREFIX, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }

    //-----------------------------------退款end--------------------------

    //-----------------------------------投诉begin------------------------


    /**
     * 根据id查询投诉信息
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id查询投诉信息(刘明宇)", notes = "根据id查询投诉信息", response = BaseResult.class)
    public BaseResult queryReportById(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据id查询投诉信息 id = {}", LOG_PREFIX, id);
        ReportRecordVO reportRecordVO = reportRecordService.queryRecordById(id);
        return BaseResult.success(reportRecordVO);
    }

    /**
     * 根据用户id或配送员id 和 主动投诉方分页查询投诉记录
     *
     * @param uid
     * @param did
     * @param activeSide
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/report", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id或配送员id 和 主动投诉方分页查询投诉记录(刘明宇)", notes = "根据用户id或配送员id 和 主动投诉方分页查询投诉记录", response = BaseResult.class)
    public BaseResult pageReport(@RequestParam(value = "uid", required = false, defaultValue = "-1") Integer uid,
                                 @RequestParam(value = "did", required = false, defaultValue = "-1") Integer did,
                                 @RequestParam(value = "activeSide", required = false, defaultValue = "-1") Integer activeSide,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                 @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                 HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据用户id或配送员id 和 主动投诉方分页查询投诉记录, uid = {}, did = {}, activeSide = {}, page = {}, size = {}", LOG_PREFIX, uid, did, activeSide, page, size);
        PageInfo<ReportRecordVO> pageInfo;
        if (uid == null || uid <= 0) {
            pageInfo = reportRecordService.pageRecordByActiveAndDID(did, activeSide, page, size, orderField, orderType);
        } else {
            pageInfo = reportRecordService.pageRecordByActiveAndUID(uid, activeSide, page, size, orderField, orderType);
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 根据投诉id删除记录
     *
     * @param id
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/report", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据投诉id删除记录(刘明宇)", notes = "根据投诉id删除记录", response = BaseResult.class)
    public BaseResult deleteReportByID(@PathVariable("id") Integer id, HttpServletRequest request) throws AppException {
        LOGGER.info("{} 根据投诉id删除记录 id = {}", LOG_PREFIX, id);
        reportRecordService.deleteRecordById(id);
        return BaseResult.success();
    }

    //-----------------------------------投诉end--------------------------
}
