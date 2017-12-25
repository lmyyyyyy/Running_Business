package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
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
import com.running.business.vo.UserVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liumingyu
 * @create 2017-12-24 下午3:09
 */
@RestController
@RequestMapping(value = "/admins")
public class RunAdminController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(RunUserController.class);

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

    /**
     * 添加管理员
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = CodeConstants.AJC_UTF8, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "添加管理员(刘明宇)", notes = "添加管理员", response = BaseResult.class)
    public BaseResult insertAdmin(@RequestBody RunAdmin admin, HttpServletRequest request) throws Exception {
        Integer adminId = RequestUtil.getAdminId(request);
        logger.info("{}添加管理员，账号为：{}", new Object[]{adminId, admin.getAdminUsername()});
        BaseResult result = null;
        try {
            if (!RegexUtils.checkMobile(admin.getAdminUsername())) {
                return BaseResult.fail(ResultEnum.USER_PHONE_REGEX_IS_NOT);
            }
            if (admin.getAdminUsername() == null || admin.getAdminUsername().trim().equals("")
                    || admin.getAdminPassword() == null || admin.getAdminPassword().trim().equals("")) {
                return BaseResult.fail(ResultEnum.INPUT_ERROR);
            }
            if (admin.getAdminPassword().length() < 6 || admin.getAdminPassword().length() > 18) {
                return BaseResult.fail(ResultEnum.USER_PASSWORD_LEN);
            }
            result = runAdminService.checkAdmin(admin.getAdminUsername());
            if (!result.getCode().equals("200")) {
                return result;
            }
            result = runAdminService.insertRunAdmin(admin);
            if (result.getCode().equals("200")) {
                Integer id = (Integer) result.getData();
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
                runAdminInfoService.insertRunAdminInfo(adminInfo);
            }
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "添加管理员失败 operationId = {},error = {}", new Object[]{adminId, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

    /**
     * 管理员登录
     *
     * @param admin    用户实体
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = CodeConstants.AJC_UTF8)
    @ApiOperation(value = "用户登录(刘明宇)", notes = "用户登录", response = BaseResult.class)
    public BaseResult login(@RequestBody RunAdmin admin, HttpServletRequest request, HttpServletResponse response) throws Exception {
        logger.info("管理员登录：" + admin.getAdminUsername());
        System.out.println("用户名" + admin.getAdminUsername());
        System.out.println("密码" + admin.getAdminPassword());
        BaseResult result = null;
        try {
            if (admin.getAdminUsername() == null || admin.getAdminUsername().trim().equals("")
                    || admin.getAdminPassword() == null || admin.getAdminPassword().trim().equals("")) {
                result = BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
                return result;
            }
            result = runAdminService.login(admin.getAdminUsername(), admin.getAdminPassword(), request, response);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "登录失败- admin = {}, error = {}" + new Object[]{admin, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

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
        logger.info("根据token获取管理员信息：", token);
        BaseResult result = null;
        try {
            result = runAdminService.getAdminByToken(token);
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
        logger.info("管理员注销", token);
        String callback = "http://localhost:8080";
        BaseResult result = null;
        try {
            result = runAdminService.logout(token);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "管理员注销失败 token = {}, error = {}", new Object[]{token, ae});
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
        logger.info("异步检查旧密码是否匹配", oldPassword);
        String jsonStr = RequestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        boolean flag;
        try {
            flag = runAdminService.checkPwd(admin.getAdminUsername(), oldPassword);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "check old pwd is error. username = {} oldpwd = {}, error = {}", new Object[]{admin.getAdminUsername(), oldPassword, ae});
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
        logger.info("修改管理员密码", newPassword);
        String jsonStr = RequestUtil.getToken(request);

        if (jsonStr == null) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        boolean flag;
        BaseResult result = null;
        try {
            flag = runAdminService.checkPwd(admin.getAdminUsername(), oldPassword);
            if (!flag) {
                return BaseResult.fail(ResultEnum.PWD_ERROR);
            }
            admin.setAdminPassword(newPassword);
            result = runAdminService.updateRunAdmin(admin);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "update pwd is error username = {}, newPassword = {}, error = {}", new Object[]{admin.getAdminUsername(), newPassword, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
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
        logger.info("获取管理员基本信息");
        AdminVO adminVO;
        Integer uid = null;
        try {
            String jsonStr = RequestUtil.getToken(request);

            if (jsonStr == null) {
                return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
            }
            RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
            uid = admin.getAdminId();
            adminVO = runAdminInfoService.getAdminVOById(uid);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "获取管理员基本信息失败 uid = {}, error = {}", new Object[]{uid, ae});
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
        Integer operator = RequestUtil.getAdminId(request);
        logger.info("{} 根据id获取管理员基本信息 id = {}", new Object[]{operator, id});
        AdminVO adminVO;
        try {
            adminVO = runAdminInfoService.getAdminVOById(id);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "获取管理员基本信息失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
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
        Integer operator = RequestUtil.getAdminId(request);
        logger.info("{} 根据id获取用户信息 id = {}", new Object[]{operator, id});
        UserVO userVO;
        try {
            userVO = runUserInfoService.getUserVOById(id);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "根据id获取用户信息 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
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
        logger.info("更新管理员信息", userAdminfo.getAdminPhone());
        Integer uid = null;
        BaseResult result;
        try {
            if (userAdminfo == null) {
                return BaseResult.fail(ResultEnum.USER_INFO_ISEMPTY);
            }
            uid = RequestUtil.getUserId(request);
            userAdminfo.setAdminId(uid);
            result = runAdminInfoService.updateRunAdminInfo(userAdminfo);
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "更新管理员信息失败 uid = {}, error = {}", new Object[]{uid, ae});
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
        Integer operator = RequestUtil.getAdminId(request);
        logger.info("{} 删除管理员 id = {}", new Object[]{operator, id});
        BaseResult result;
        try {
            result = runAdminService.delRunAdminByID(id);
            if (result.getCode().equals("200")) {
                runAdminInfoService.delRunAdminInfoByID(id);
            }
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "删除管理员失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
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
        Integer operator = RequestUtil.getAdminId(request);
        logger.info("{} 删除用户 id = {}", new Object[]{operator, id});
        BaseResult result;
        try {
            result = runUserService.delUser(id);
            if (result.getCode().equals("200")) {
                runUserInfoService.delRunUserInfo(id);
                runUserAddressService.delAllRunUserAddressByUID(id);
                runUserPreferenceService.delAllRunUserPreferenceByUID(id);
                runUserCouponService.delAllRunUserCoupon(id);
                runUserBalanceService.delRunUserBalance(id);
            }
        } catch (AppException ae) {
            logger.error(LOG_PREFIX + "删除用户失败 operator = {}, id = {}, error = {}", new Object[]{operator, id, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }
}
