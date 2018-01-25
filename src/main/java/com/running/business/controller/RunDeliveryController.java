package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceRecordService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.util.IdcardValidator;
import com.running.business.util.JsonUtils;
import com.running.business.util.RegexUtils;
import com.running.business.util.RequestUtil;
import com.running.business.vo.DeliveryVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    /**
     * 验证账号是否可用
     *
     * @param phone 用户名
     * @return
     */
    @RequestMapping(value = "/check/{phone}", method = RequestMethod.GET)
    @ApiOperation(value = "验证账号是否可用(刘明宇)", notes = "验证账号是否可用", response = Object.class)
    public Object checkData(@PathVariable String phone) throws Exception {
        if (phone == null || "".equals(phone)) {
            return BaseResult.fail(ResultEnum.DELIVERY_PHONE_REGEX_IS_ERROR);
        }
        if (!RegexUtils.checkMobile(phone)) {
            return BaseResult.fail(ResultEnum.DELIVERY_PHONE_REGEX_IS_ERROR);
        }
        LOGGER.info("验证账号是否可用：" + phone);
        boolean flag;
        try {
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
        BaseResult result = null;
        Integer did;
        try {
            did = runDeliveryuserService.saveRunDeliveryuser(user);
        } catch (AppException ae) {
            LOGGER.error("{} 注册配送员失败 user = {}", LOG_PREFIX, user);
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(user);
    }

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
     * 配送员登录
     *
     * @param user     用户实体
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "配送员登录(刘明宇)", notes = "配送员登录", response = BaseResult.class)
    public BaseResult login(@RequestBody RunDeliveryuser user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (user == null) {
            return BaseResult.fail(ResultEnum.DELIVERY_INFO_ISEMPTY);
        }
        LOGGER.info("配送员登录：" + user.getUserphone());
        BaseResult result;
        String token;
        try {
            if (user.getUserphone() == null || user.getUserphone().trim().equals("")
                    || user.getPassword() == null || user.getPassword().trim().equals("")) {
                result = BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
                return result;
            }
            token = runDeliveryuserService.login(user.getUserphone(), user.getPassword(), request, response);
            if (token == null || "".equals(token)) {
                BaseResult.fail(ResultEnum.INNER_ERROR);
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "登录失败- user = {}, error = {}" + new Object[]{user, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return BaseResult.success(token);
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
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
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
}
