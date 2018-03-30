package com.running.business.controller;

import com.github.pagehelper.PageInfo;
import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.dto.RefundDTO;
import com.running.business.exception.AppException;
import com.running.business.interceptor.PermissionInterceptor;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunOrderPay;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserCoupon;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RefundApplyService;
import com.running.business.service.RefundRecordService;
import com.running.business.service.ReportRecordService;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.service.RunOrderPayService;
import com.running.business.service.RunOrderService;
import com.running.business.service.RunUserAddressService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserCouponService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserPreferenceService;
import com.running.business.service.RunUserService;
import com.running.business.util.JsonUtils;
import com.running.business.util.RandomUtil;
import com.running.business.util.RegexUtils;
import com.running.business.util.RequestUtil;
import com.running.business.vo.AdminVO;
import com.running.business.vo.DeliveryDetailVO;
import com.running.business.vo.OrderVO;
import com.running.business.vo.RefundApplyVO;
import com.running.business.vo.RefundRecordVO;
import com.running.business.vo.ReportRecordVO;
import com.running.business.vo.UserDetailVO;
import com.running.business.vo.UserVO;
import com.running.business.websocket.SystemWebSocketHandler;
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
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private RunDeliveryuserService runDeliveryuserService;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunDeliveryBalanceService runDeliveryBalanceService;

    @Autowired
    private RunDeliveryDistanceService runDeliveryDistanceService;

    @Autowired
    private RunDeliveryAddressService runDeliveryAddressService;

    @Autowired
    private RunOrderService runOrderService;

    @Autowired
    private RunOrderPayService runOrderPayService;

    @Autowired
    private static SystemWebSocketHandler systemWebSocketHandler;

    //============================管理员begin===========================

    /**
     * 添加管理员（操作别人）
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "添加管理员(刘明宇)", notes = "添加管理员", response = BaseResult.class)
    public BaseResult insertAdmin(@RequestBody RunAdmin admin, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (admin.getAdminUsername() == null || admin.getAdminUsername().trim().equals("")
                || admin.getAdminPassword() == null || admin.getAdminPassword().trim().equals("")) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (admin.getAdminPassword().length() < 6 || admin.getAdminPassword().length() > 18) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        if (!RegexUtils.checkMobile(admin.getAdminUsername())) {
            return BaseResult.fail(ResultEnum.USER_PHONE_REGEX_IS_NOT);
        }
        LOGGER.info("{}添加管理员，账号为：{}", new Object[]{admin.getAdminId(), admin.getAdminUsername()});
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
            LOGGER.error(LOG_PREFIX + "添加管理员失败 operationId = {},error = {}", new Object[]{admin.getAdminId(), ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(admin);
    }


    /**
     * 根据token获取管理员信息(操作自己)
     *
     * @param token    token字符串
     * @param callback 回调
     * @return
     */
    @RequestMapping(value = "/token/{token}", method = RequestMethod.GET)
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
     * 注销(操作自己)
     *
     * @param token    token字符串
     * @param response 响应
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout/{token}", method = RequestMethod.POST)
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
     * 异步检查旧密码是否匹配(操作自己)
     *
     * @param oldPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkoldpwd", method = RequestMethod.GET)
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
     * 修改管理员密码(操作自己)
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatepwd", method = RequestMethod.PUT)
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
     * 获取管理员信息(操作自己)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
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
     * 更新管理员信息(操作自己)
     *
     * @param userAdminfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = RequestMethod.PUT)
    @ApiOperation(value = "更新管理员信息(刘明宇)", notes = "更新管理员信息", response = BaseResult.class)
    public BaseResult updateAdminInfo(@RequestBody RunAdminInfo userAdminfo, HttpServletRequest request) throws Exception {
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
     * 根据id获取管理员信息(操作别人)
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
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
     * 根据id删除管理员(操作别人)
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admin/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除管理员(刘明宇)", notes = "根据id删除管理员", response = BaseResult.class)
    public BaseResult delAdmin(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
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
     * 修改管理员密码(操作别人)
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/others/password", method = RequestMethod.PUT)
    @ApiOperation(value = "修改管理员密码(刘明宇)", notes = "修改管理员密码", response = BaseResult.class)
    public BaseResult modifyAdminPwd(@RequestParam(value = "adminId", required = true) Integer adminId,
                                     @RequestParam("oldPassword") String oldPassword,
                                     @RequestParam("newPassword") String newPassword,
                                     HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.ADMIN_PASSWORD_REGEX_ERROR);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.ADMIN_PASSWORD_NEW_AND_OLD_IS_SAME);
        }
        LOGGER.info("修改管理员密码", newPassword);
        RunAdmin admin = null;
        boolean flag;
        try {
            admin = (RunAdmin) runAdminService.getRunAdminByID(adminId).getData();
            if (admin == null) {
                return BaseResult.fail(ResultEnum.ADMIN_ID_ERROR);
            }
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
     * 更新管理员信息(操作别人)
     *
     * @param userAdminfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/others/info", method = RequestMethod.PUT)
    @ApiOperation(value = "更新管理员信息(刘明宇)", notes = "更新管理员信息", response = BaseResult.class)
    public BaseResult updateOtherAdminInfo(@RequestBody RunAdminInfo userAdminfo, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("更新管理员信息", userAdminfo.getAdminPhone());
        Integer adminId = null;
        BaseResult result;
        try {
            if (userAdminfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            adminId = requestUtil.getAdminId(request);
            userAdminfo.setAdminId(adminId);
            result = runAdminInfoService.updateRunAdminInfo(userAdminfo);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "更新管理员信息失败 adminId = {}, error = {}", new Object[]{adminId, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * \
     * 分页获取管理员信息
     *
     * @param status
     * @param isDelete
     * @param page
     * @param size
     * @param orderField
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "分页获取管理员信息(刘明宇)", notes = "分页获取管理员信息", response = BaseResult.class)
    @RequestMapping(value = "", method = RequestMethod.GET)
    public BaseResult pageAdmins(@RequestParam(value = "status", required = false, defaultValue = "false") Boolean status,
                                 @RequestParam(value = "isDelete", required = false, defaultValue = "false") Boolean isDelete,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                 @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                 @RequestParam(value = "orderField", required = false, defaultValue = "admin_time") String orderField,
                                 @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                 HttpServletRequest request) throws AppException {
        LOGGER.info("{} 分页获取管理员信息 page = {}, size = {}", LOG_PREFIX, page, size);
        PageInfo<AdminVO> pageInfo = null;
        pageInfo = runAdminService.pageAdminVO(status, isDelete, page, size, orderField, orderType);
        return BaseResult.success(pageInfo);
    }

    //=========================管理员end==============================

    //=========================用户begin==============================

    /**
     * 添加用户
     *
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户(刘明宇)", notes = "添加用户", response = BaseResult.class)
    public BaseResult register(@RequestBody RunUser user, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (user.getUserphone() == null || "".equals(user.getUserphone().trim())
                || user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 18) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        if (!RegexUtils.checkMobile(user.getUserphone())) {
            return BaseResult.fail(ResultEnum.USER_PHONE_REGEX_IS_NOT);
        }

        LOGGER.info("注册用户，账号为：" + user.getUserphone());
        BaseResult result;
        try {
            result = runUserService.checkUser(user.getUserphone());
            if (!result.getCode().equals("200")) {
                return result;
            }
            result = runUserService.saveUser(user);
            if (result.getCode().equals("200")) {
                int uid = user.getUid();
                RunUserBalance balance = new RunUserBalance();
                balance.setUid(uid);
                balance.setUserBalance(0.00d);
                balance.setUpdateTime(new Date());
                runUserBalanceService.saveRunUserBalance(balance);
                RunUserInfo runUserInfo = new RunUserInfo();
                runUserInfo.setUid(uid);
                runUserInfo.setUserPhone(user.getUserphone());
                runUserInfo.setUserPhoto("/img/default.jpg");
                runUserInfo.setUserPoint(0);
                runUserInfo.setUserGender(false);
                String name = RandomUtil.generateRandomDigitString(5);
                int count = 0;
                while (!runUserInfoService.checkNameUnique(name)) {
                    if (count >= 5) {
                        return BaseResult.fail(ResultEnum.Number_THAN_BIG);
                    }
                    name = RandomUtil.generateRandomDigitString(5);
                    count++;
                }
                runUserInfo.setUserName("游客_" + name);
                runUserInfoService.saveRunUserInfo(runUserInfo);
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "注册用户失败- user = {}, error = {}" + new Object[]{user, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(user);
    }

    /**
     * 根据id获取用户信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取用户信息(刘明宇)", notes = "根据id获取用户信息", response = BaseResult.class)
    public BaseResult getUserInfoById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 根据id获取用户信息 id = {}", new Object[]{operator, id});
        UserDetailVO userVO;
        try {
            userVO = runUserService.queryUserDetailVO(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "根据id获取用户信息 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(userVO);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除用户(刘明宇)", notes = "根据id删除用户", response = BaseResult.class)
    public BaseResult delUser(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
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
     * 更新用户信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/info", method = RequestMethod.PUT)
    @ApiOperation(value = "更新用户信息(刘明宇)", notes = "更新用户信息", response = BaseResult.class)
    public BaseResult updateUserInfo(@RequestBody RunUserInfo userInfo, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("更新用户信息", userInfo.getUserPhone());
        try {
            if (userInfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            runUserInfoService.updateRunUserInfo(userInfo);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "更新用户信息失败 error = {}", new Object[]{ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 修改用户密码
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users/password", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户密码(刘明宇)", notes = "修改用户密码", response = BaseResult.class)
    public BaseResult modifyUserPwd(@RequestParam(value = "uid", required = true) Integer uid,
                                    @RequestParam("oldPassword") String oldPassword,
                                    @RequestParam("newPassword") String newPassword,
                                    HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_OLD_AND_NEW_IS_SAME);
        }
        LOGGER.info("修改用户密码", newPassword);
        boolean flag;
        BaseResult result = null;
        RunUser user = null;
        try {
            user = (RunUser) runUserService.getRunUser(uid).getData();
            if (user == null) {
                return BaseResult.fail(ResultEnum.USER_ID_IS_ERROR);
            }
            flag = runUserService.checkPwd(user.getUserphone(), oldPassword);
            if (!flag) {
                return BaseResult.fail(ResultEnum.PWD_ERROR);
            }
            user.setPassword(newPassword);
            result = runUserService.updateUser(user);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "update pwd is error username = {}, newPassword = {}, error = {}", new Object[]{user.getUserphone(), newPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 给用户充值
     *
     * @param uid
     * @param amount
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "给用户充值(刘明宇)", notes = "给用户充值", response = BaseResult.class)
    @RequestMapping(value = "/users/balance/{uid}", method = RequestMethod.PUT)
    public BaseResult addUserMoney(@PathVariable Integer uid,
                                   @RequestParam(value = "amount", required = false, defaultValue = "0") Double amount,
                                   HttpServletRequest request) throws AppException {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (amount <= 0) {
            return BaseResult.fail(ResultEnum.AMOUNT_CAN_NOT_LESS_ZERO);
        }
        RunUserBalance balance = runUserBalanceService.getRunUserBalanceByUID(uid);
        if (balance == null) {
            balance.setUserBalance(amount);
            balance.setUpdateTime(new Date());
            runUserBalanceService.saveRunUserBalance(balance);
        }
        balance.setUserBalance(balance.getUserBalance() + amount);
        balance.setUid(uid);
        runUserBalanceService.updateRunUserBalance(balance);
        return BaseResult.success();
    }

    /**
     * 分页获取所有的用户列表
     *
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有的用户列表(刘明宇)", notes = "分页获取所有的用户列表", response = BaseResult.class)
    public BaseResult pageUsers(@RequestParam(value = "status", required = false) Boolean status,
                                @RequestParam(value = "isDelete", required = false) Boolean isDelete,
                                @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        if (!permissionInterceptor.isInvoke(request)) {
            LOGGER.error("{} 当前用户没有权限进行操作 operator = {}", LOG_PREFIX, operator);
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("{} 分页获取所有的用户列表 operator = {}", new Object[]{LOG_PREFIX, operator});
        PageInfo<UserVO> pageInfo;
        try {
            pageInfo = runUserService.pageAllRunUser(status, isDelete, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取用户列表失败 operator = {}, error = {}", LOG_PREFIX, operator, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 根据用户id和订单状态查询用户各种状态订单（分页）(管理员操作)
     *
     * @param status 订单状态
     * @return
     */
    @RequestMapping(value = "/users/orders/{uid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id和订单状态查询用户各种状态订单(管理员操作)/分页(刘明宇)", notes = "根据用户id和订单状态查询用户各种状态订单（分页）", response = BaseResult.class)
    public BaseResult pageOrdersByUIDAndStatus(@PathVariable Integer uid,
                                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                               HttpServletRequest request) throws AppException {
        PageInfo<OrderVO> pageInfo = runOrderService.pageOrderByUIDAndStatus(keyword, uid, status, page, size, orderType);
        return BaseResult.success(pageInfo);
    }

    /**
     * 根据用户id和支付类型查询用户订单支付记录
     *
     * @param uid
     * @param payType
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/users/pays/{uid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据用户id和支付类型查询用户订单支付记录(管理员操作)/分页(刘明宇)", notes = "根据用户id和支付类型查询用户订单支付记录（分页）", response = BaseResult.class)
    public BaseResult pageOrderPaysByUID(@PathVariable Integer uid,
                                         @RequestParam(value = "payType", required = false, defaultValue = "-1") Integer payType,
                                         @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                         @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                         HttpServletRequest request) throws AppException {
        PageInfo<RunOrderPay> payPageInfo = runOrderPayService.pagePaysByUIDAndType(uid, payType, page, size, orderType);
        return BaseResult.success(payPageInfo);
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
    @RequestMapping(value = "/users/fefunds/{uid}", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取退款记录(刘明宇)", notes = "分页获取退款记录", response = BaseResult.class)
    public BaseResult pageRefundRecord(@PathVariable Integer uid,
                                       @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                       @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                       @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                       @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                       HttpServletRequest request) throws AppException {
        LOGGER.info("{} 分页获取退款记录 page = {}, size = {}", LOG_PREFIX, page, size);
        PageInfo<RefundRecordVO> pageInfo;
        try {
            pageInfo = refundRecordService.pageRefundByUID(uid, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取退款记录失败 uid = {}, page = {}, size = {}, error = {}", LOG_PREFIX, uid, page, size, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
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
    @RequestMapping(value = "/users/apply/{uid}", method = RequestMethod.GET)
    @ApiOperation(value = "根据订单状态分页查询退款申请（用户查询）(刘明宇)", notes = "根据订单状态分页查询退款申请", response = BaseResult.class)
    public BaseResult pageRefundApply(@PathVariable Integer uid,
                                      @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                      @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                      @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                      @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                      HttpServletRequest request) throws AppException {
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

    //===================================用户end===============================

    //===================================配送员begin===========================

    /**
     * 添加配送员
     *
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @ApiOperation(value = "添加配送员(刘明宇)", notes = "添加配送员", response = BaseResult.class)
    public BaseResult register(@RequestBody RunDeliveryuser user, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (user == null) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (user.getUserphone() == null || "".equals(user.getUserphone().trim())
                || user.getPassword() == null || "".equals(user.getPassword().trim())) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR);
        }
        if (!RegexUtils.checkMobile(user.getUserphone())) {
            return BaseResult.fail(ResultEnum.DELIVERY_PHONE_REGEX_IS_ERROR);
        }
        if (user.getPassword().length() < 6 || user.getPassword().length() > 18) {
            return BaseResult.fail(ResultEnum.DELIVERY_PASSWORD_LEN);
        }
        LOGGER.info("注册用户，账号为：" + user.getUserphone());
        Integer did;
        try {
            boolean flag = runDeliveryuserService.checkRunDeliveryuser(user.getUserphone());
            if (!flag) {
                return BaseResult.fail(ResultEnum.TELTPHONE_DELIVERY);
            }
            did = runDeliveryuserService.saveRunDeliveryuser(user);
            if (did != null && did > 0) {
                RunDeliveryInfo info = new RunDeliveryInfo();
                info.setDid(did);
                info.setLevel("一条腿");
                info.setPhone(user.getUserphone());
                info.setReportedTimes(0);
                info.setAddressId(-1);
                info.setCard("");
                info.setPhoto("/img/default.jpg");
                info.setGender(false);
                info.setPoint(0);
                String name = RandomUtil.generateRandomDigitString(5);
                int count = 0;
                while (!runDeliveryInfoService.checkNameUnique(name)) {
                    if (count >= 5) {
                        return BaseResult.fail(ResultEnum.Number_THAN_BIG);
                    }
                    name = RandomUtil.generateRandomDigitString(5);
                    count++;
                }
                runDeliveryInfoService.saveRunDeliveryInfo(info);
                info.setName("游客_" + name);
                RunDeliveryDistance distance = new RunDeliveryDistance();
                distance.setDid(did);
                distance.setSendDistance(Double.valueOf(Config.ORDER_DISTANCE));
                distance.setDeliveryDistance(Double.valueOf(Config.ORDER_SOURCE_ADDRESS_DISTANCE));
                distance.setViewOrderDistance(Double.valueOf(Config.ORDER_TARGET_ADDRESS_DISTANCE));
                distance.setUpdateTime(new Date());
                runDeliveryDistanceService.saveRunDeliveryDistance(distance);
                RunDeliveryBalance balance = new RunDeliveryBalance();
                balance.setDid(did);
                balance.setUpdateTime(new Date());
                balance.setDeliveryBalance(0.0);
                runDeliveryBalanceService.saveRunDeliveryBalance(balance);
            }
        } catch (AppException ae) {
            LOGGER.error("{} 注册配送员失败 user = {}", LOG_PREFIX, user);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(user);
    }


    /**
     * 根据id获取配送员信息
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据id获取配送员信息(刘明宇)", notes = "根据id获取配送员信息", response = BaseResult.class)
    public BaseResult getDeliveryInfoById(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 根据id获取配送员信息 id = {}", new Object[]{operator, id});
        DeliveryDetailVO userVO;
        try {
            userVO = runDeliveryuserService.queryDeliveryDetailVO(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "根据id获取配送员信息 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(userVO);
    }

    /**
     * 根据id删除配送员
     *
     * @param id
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "根据id删除配送员(刘明宇)", notes = "根据id删除配送员", response = BaseResult.class)
    public BaseResult delDelivery(@PathVariable Integer id, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        Integer operator = requestUtil.getAdminId(request);
        LOGGER.info("{} 根据id删除配送员 id = {}", new Object[]{operator, id});
        try {
            runDeliveryuserService.deleteRunDeliveryuser(id);
            runDeliveryInfoService.deleteRunDeliveryInfoByID(id);
            runDeliveryBalanceService.deleteRunDeliveryBalanceByDID(id);
            runDeliveryAddressService.deleteAllRunDeliveryAddressByDID(id);
            runDeliveryDistanceService.deleteRunDeliveryDistanceByDID(id);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "根据id删除配送员失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 更新配送员信息
     *
     * @param userInfo
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys/info", method = RequestMethod.PUT)
    @ApiOperation(value = "更新配送员信息(刘明宇)", notes = "更新配送员信息", response = BaseResult.class)
    public BaseResult updateDeliveryInfo(@RequestBody RunDeliveryInfo userInfo, HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("更新配送员信息", userInfo.getPhone());
        try {
            if (userInfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            runDeliveryInfoService.updateRunDeliveryInfo(userInfo);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "更新配送员信息 error = {}", new Object[]{ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success();
    }

    /**
     * 修改配送员密码
     *
     * @param newPassword
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys/password", method = RequestMethod.PUT, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "修改配送员密码(刘明宇)", notes = "修改配送员密码", response = BaseResult.class)
    public BaseResult modifyDeliveryPwd(@RequestParam(value = "did", required = true) Integer did,
                                        @RequestParam("oldPassword") String oldPassword,
                                        @RequestParam("newPassword") String newPassword,
                                        HttpServletRequest request) throws Exception {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        if (oldPassword == null || "".equals(oldPassword) || newPassword == null || "".equals(newPassword)) {
            return BaseResult.fail(ResultEnum.DELIVERY_PASSWORD_LEN);
        }
        if (oldPassword.equals(newPassword)) {
            return BaseResult.fail(ResultEnum.USER_PASSWORD_OLD_AND_NEW_IS_SAME);
        }
        LOGGER.info("修改配送员密码", newPassword);
        boolean flag;
        RunDeliveryuser user = null;
        try {
            user = runDeliveryuserService.getRunDeliveryuserById(did);
            if (user == null) {
                return BaseResult.fail(ResultEnum.DELIVERY_ID_IS_ERROR);
            }
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
        return BaseResult.success();
    }

    /**
     * 给配送员充值
     *
     * @param did
     * @param amount
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "给配送员充值(刘明宇)", notes = "给配送员充值", response = BaseResult.class)
    @RequestMapping(value = "/deliverys/balance/{did}", method = RequestMethod.PUT)
    public BaseResult addDeliveryMoney(@PathVariable Integer did,
                                       @RequestParam(value = "amount", required = false, defaultValue = "0") Double amount,
                                       HttpServletRequest request) throws AppException {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("{} 给配送员充值 did = {}", LOG_PREFIX, did);
        if (amount <= 0) {
            return BaseResult.fail(ResultEnum.AMOUNT_CAN_NOT_LESS_ZERO);
        }
        RunDeliveryBalance balance = runDeliveryBalanceService.getRunDeliveryBalanceByDID(did);
        if (balance == null) {
            balance.setDeliveryBalance(amount);
            balance.setUpdateTime(new Date());
            runDeliveryBalanceService.saveRunDeliveryBalance(balance);
        }
        balance.setDeliveryBalance(balance.getDeliveryBalance() + amount);
        balance.setDid(did);
        runDeliveryBalanceService.updateRunDeliveryBalance(balance);
        return BaseResult.success();
    }

    /**
     * 分页获取所有的配送员列表
     *
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有的配送员列表(刘明宇)", notes = "分页获取所有的配送员列表", response = BaseResult.class)
    public BaseResult pageDeliverys(@RequestParam(value = "status", required = false) Boolean status,
                                    @RequestParam(value = "isDelete", required = false) Boolean isDelete,
                                    @RequestParam(value = "able", required = false) Boolean able,
                                    @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                    @RequestParam(value = "orderField", required = false, defaultValue = "add_time") String orderField,
                                    @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                    HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        if (!permissionInterceptor.isInvoke(request)) {
            LOGGER.error("{} 当前用户没有权限进行操作 operator = {}", LOG_PREFIX, operator);
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("{} 分页获取所有的配送员列表 operator = {}", new Object[]{LOG_PREFIX, operator});
        PageInfo<DeliveryDetailVO> pageInfo;
        try {
            pageInfo = runDeliveryuserService.pageAllRunDeliveryuser(status, isDelete, able, page, size, orderField, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取所有的配送员列表失败 operator = {}, error = {}", LOG_PREFIX, operator, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 分页获取所有等待审核的配送员列表
     *
     * @param page
     * @param size
     * @param orderType
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deliverys/able", method = RequestMethod.GET)
    @ApiOperation(value = "分页获取所有的配送员列表(刘明宇)", notes = "分页获取所有的配送员列表", response = BaseResult.class)
    public BaseResult pageAbleDeliverys(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", required = false, defaultValue = "20") Integer size,
                                        @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                        HttpServletRequest request) throws Exception {
        Integer operator = requestUtil.getAdminId(request);
        if (!permissionInterceptor.isInvoke(request)) {
            LOGGER.error("{} 当前用户没有权限进行操作 operator = {}", LOG_PREFIX, operator);
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("{} 分页获取所有的配送员列表 operator = {}", new Object[]{LOG_PREFIX, operator});
        PageInfo<RunDeliveryuser> pageInfo;
        try {
            pageInfo = runDeliveryuserService.pageAbleRunDeliveryuser(page, size, orderType);
        } catch (AppException ae) {
            LOGGER.error("{} 分页获取所有的配送员列表失败 operator = {}, error = {}", LOG_PREFIX, operator, ae);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(pageInfo);
    }

    /**
     * 审核配送员账号 0:false:禁用 ; 1:true:启用
     *
     * @param did
     * @param able
     * @param request
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/deliverys/able", method = RequestMethod.PUT)
    @ApiOperation(value = "审核配送员账号(刘明宇)", notes = "审核配送员账号", response = BaseResult.class)
    public BaseResult ableDelivery(@PathVariable Integer did,
                                   @RequestParam(value = "able", required = true, defaultValue = "ture") Boolean able,
                                   HttpServletRequest request) throws AppException {
        if (!permissionInterceptor.isInvoke(request)) {
            return BaseResult.fail(ResultEnum.PERMISSION_ERROR);
        }
        LOGGER.info("{} 审核配送员账号 did = {}", LOG_PREFIX, did);
        runDeliveryuserService.updateAvailable(did, able);
        return BaseResult.success();
    }

    /**
     * 根据配送员id和订单状态查询配送员各种状态订单（分页）(管理员操作)
     *
     * @param status 订单状态
     * @return
     */
    @RequestMapping(value = "/deliverys/orders/{did}", method = RequestMethod.GET)
    @ApiOperation(value = "根据配送员id和订单状态查询配送员各种状态订单(管理员操作)/分页(刘明宇)", notes = "根据配送员id和订单状态查询配送员各种状态订单（分页）", response = BaseResult.class)
    public BaseResult pageOrdersByDIDAndStatus(@PathVariable Integer did,
                                               @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
                                               @RequestParam(value = "status", required = false, defaultValue = "-1") Integer status,
                                               @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
                                               @RequestParam(value = "orderType", required = false, defaultValue = "DESC") String orderType,
                                               HttpServletRequest request) throws AppException {
        PageInfo<OrderVO> pageInfo = runOrderService.pageRunOrderByDIDAndStatus(did, status, keyword, page, size, orderType);
        return BaseResult.success(pageInfo);
    }

    //===================================配送员end=============================

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
        LOGGER.info("{} 分页获取优惠券", LOG_PREFIX);
        return BaseResult.success(runUserCouponService.pageRunUserCouponByStatus(status, page, size, orderField, orderType));
    }

    /**
     * 添加优惠券（通知在线用户）
     *
     * @param coupon
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "添加优惠券(刘明宇)", notes = "添加优惠券", response = BaseResult.class)
    @RequestMapping(value = "/coupons", method = RequestMethod.POST)
    public BaseResult saveCoupon(@RequestBody RunUserCoupon coupon, HttpServletRequest request) throws AppException {
        if (coupon == null) {
            return BaseResult.fail(ResultEnum.USER_COUPON_INFO_EMTPY);
        }
        LOGGER.info("{} 添加优惠券", LOG_PREFIX);
        runUserCouponService.saveRunUserCoupon(coupon);
        return BaseResult.success();
    }

    /**
     * 给固定用户列表添加优惠券（通知在线用户）
     *
     * @param coupon
     * @param userIdList
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "给固定用户列表添加优惠券(刘明宇)", notes = "给固定用户列表添加优惠券", response = BaseResult.class)
    @RequestMapping(value = "/coupons/users", method = RequestMethod.POST)
    public BaseResult saveCouponByUsers(@RequestBody RunUserCoupon coupon, @RequestParam String userIdList, HttpServletRequest request) throws AppException {
        if (coupon == null) {
            return BaseResult.fail(ResultEnum.USER_COUPON_INFO_EMTPY);
        }
        if (userIdList == null || "".equals(userIdList.trim())) {
            return BaseResult.fail(ResultEnum.USER_LIST_IS_EMTPY);
        }
        String[] userStr = userIdList.split(",");
        if (userStr == null || userStr.length == 0) {
            return BaseResult.fail(ResultEnum.USER_LIST_IS_EMTPY);
        }
        List<Integer> userIds = new ArrayList<>(userStr.length);
        for (String userId : userStr) {
            if (userId == null || "".equals(userId)) {
                continue;
            }
            if (!RegexUtils.checkDigit(userId)) {
                continue;
            }
            userIds.add(Integer.parseInt(userId));
        }
        LOGGER.info("{} 给固定用户列表添加优惠券 userIds = {}", LOG_PREFIX, userIdList);
        runUserCouponService.saveRunUserCouponByUsers(coupon, userIds);
        return BaseResult.success();
    }

    /**
     * 更新优惠券
     *
     * @param coupon
     * @param request
     * @return
     * @throws AppException
     */
    @ApiOperation(value = "更新优惠券(刘明宇)", notes = "更新优惠券", response = BaseResult.class)
    @RequestMapping(value = "/coupon", method = RequestMethod.PUT)
    public BaseResult updateCoupon(@RequestBody RunUserCoupon coupon, HttpServletRequest request) throws AppException {
        if (coupon == null) {
            return BaseResult.fail(ResultEnum.USER_COUPON_INFO_EMTPY);
        }
        LOGGER.info("{} 更新优惠券", LOG_PREFIX);
        runUserCouponService.updateRunUserCoupon(coupon);
        return BaseResult.success();
    }

    /**
     * 向所有在线用户发送自定义消息
     *
     * @param message
     * @param request
     * @return
     */
    @ApiOperation(value = "向所有在线用户发送自定义消息(刘明宇)", notes = "向所有在线用户发送自定义消息", response = BaseResult.class)
    @RequestMapping(value = "/websocket/send/all", method = RequestMethod.POST)
    public BaseResult sendMessage2All(@RequestParam(value = "message", required = true) String message, HttpServletRequest request) {
        if (message == null || "".equals(message.trim())) {
            return BaseResult.fail(ResultEnum.SEND_MESSAGE_IS_EMPTY);
        }
        LOGGER.info("{} 向所有在线用户发送自定义消息 message = {}", LOG_PREFIX, message);
        systemWebSocketHandler.sendMessageToUsers(new TextMessage(message));
        return null;
    }

    /**
     * 向指定用户列表发送自定义消息
     *
     * @param phones
     * @param message
     * @param request
     * @return
     */
    @ApiOperation(value = "向指定用户列表发送自定义消息(刘明宇)", notes = "向指定用户列表发送自定义消息", response = BaseResult.class)
    @RequestMapping(value = "/websocket/send/users", method = RequestMethod.POST)
    public BaseResult sendMessage2Users(@RequestBody List<String> phones, @RequestParam(value = "message", required = true) String message, HttpServletRequest request) {
        if (phones == null || phones.isEmpty()) {
            return BaseResult.fail(ResultEnum.USER_LIST_IS_EMTPY);
        }
        if (message == null || "".equals(message.trim())) {
            return BaseResult.fail(ResultEnum.SEND_MESSAGE_IS_EMPTY);
        }
        LOGGER.info("{} 向指定用户列表发送自定义消息 phones = [{}], message = {}", LOG_PREFIX, phones, message);
        systemWebSocketHandler.sendMessage2UserList(phones, new TextMessage(message));
        return null;
    }

}
