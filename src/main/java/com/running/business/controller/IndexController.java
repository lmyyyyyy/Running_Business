package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.CodeConstants;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.enums.HelpBuyGoodsEnum;
import com.running.business.enums.HelpQueueTypeEnum;
import com.running.business.enums.HelpSendGoodsEnum;
import com.running.business.exception.AppException;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunAdminInfo;
import com.running.business.pojo.RunDeliveryAddress;
import com.running.business.pojo.RunDeliveryBalance;
import com.running.business.pojo.RunDeliveryDistance;
import com.running.business.pojo.RunDeliveryInfo;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunUser;
import com.running.business.pojo.RunUserBalance;
import com.running.business.pojo.RunUserInfo;
import com.running.business.service.RunAdminInfoService;
import com.running.business.service.RunAdminService;
import com.running.business.service.RunDeliveryAddressService;
import com.running.business.service.RunDeliveryBalanceService;
import com.running.business.service.RunDeliveryDistanceService;
import com.running.business.service.RunDeliveryInfoService;
import com.running.business.service.RunDeliveryuserService;
import com.running.business.service.RunUserBalanceService;
import com.running.business.service.RunUserInfoService;
import com.running.business.service.RunUserService;
import com.running.business.util.IdcardValidator;
import com.running.business.util.RandomUtil;
import com.running.business.util.RegexUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-01-22 下午1:07
 */
@RestController
@RequestMapping("/index")
@Api(value = "注册登录模块", tags = {"注册登录模块"})
public class IndexController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RunUserController.class);

    private static final String LOG_PREFIX = "【注册登录模块】 ";

    @Autowired
    private RunUserService runUserService;

    @Autowired
    private RunUserBalanceService runUserBalanceService;

    @Autowired
    private RunUserInfoService runUserInfoService;

    @Autowired
    private RunDeliveryuserService runDeliveryuserService;

    @Autowired
    private RunDeliveryInfoService runDeliveryInfoService;

    @Autowired
    private RunDeliveryBalanceService runDeliveryBalanceService;

    @Autowired
    private RunDeliveryDistanceService runDeliveryDistanceService;

    @Autowired
    private RunAdminService runAdminService;

    @Autowired
    private RunAdminInfoService runAdminInfoService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toIndex() {
        return "login";
    }


    //---------------------------------用户模块---------------------------------

    /**
     * 验证账号是否可用
     *
     * @param username 用户名
     * @param callback 回调
     * @return
     */
    @RequestMapping(value = "/users/check/{username}", method = RequestMethod.GET)
    @ApiOperation(value = "验证账号是否可用(刘明宇)", notes = "验证账号是否可用", response = Object.class)
    public Object checkData(@PathVariable String username, String callback) throws Exception {
        if (StringUtils.isBlank(username)) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
        }
        LOGGER.info("验证账号是否可用：" + username);
        BaseResult result = null;
        //校验出错
        try {
            if (null != result) {
                if (null != callback) {
                    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                    mappingJacksonValue.setJsonpFunction(callback);
                    return mappingJacksonValue;
                } else {
                    return result;
                }
            }
            result = runUserService.checkUser(username);
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "验证失败 username = {} error = {}" + new Object[]{username, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    /**
     * 添加用户
     *
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    @ApiOperation(value = "添加用户(刘明宇)", notes = "添加用户", response = BaseResult.class)
    public BaseResult register(@RequestBody RunUser user) throws Exception {
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
     * 用户登录
     *
     * @param user     用户实体
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/users/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录(刘明宇)", notes = "用户登录", response = BaseResult.class)
    public BaseResult login(@RequestBody RunUser user, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (user.getUserphone() == null || user.getUserphone().trim().equals("")
                || user.getPassword() == null || user.getPassword().trim().equals("")) {
            return BaseResult.fail(ResultEnum.INPUT_ERROR.getCode(), ResultEnum.INPUT_ERROR.getMsg());
        }
        LOGGER.info("用户登录：" + user.getUserphone());
        BaseResult result = null;
        try {
            result = runUserService.login(user.getUserphone(), user.getPassword(), request, response);
            if(result.getCode().equals(ResultEnum.SUCCESS.getCode())) {
                HttpSession session = request.getSession();
                session.setAttribute(Config.SESSION_USERNAME, user.getUserphone());
            }
        } catch (AppException ae) {
            LOGGER.error(LOG_PREFIX + "登录失败- user = {}, error = {}" + new Object[]{user, ae});
            return BaseResult.fail(ae.getErrorCode(), ae.getMessage());
        }
        return result;
    }

//---------------------------------配送员模块---------------------------------

    /**
     * 验证账号是否可用
     *
     * @param phone 用户名
     * @return
     */
    @RequestMapping(value = "/delivery/check/{phone}", method = RequestMethod.GET)
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
     * 配送员登录
     *
     * @param user     用户实体
     * @param request  请求
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/delivery/login", method = RequestMethod.POST)
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
     * 添加配送员
     *
     * @param user 用户实体
     * @return
     */
    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
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
                info.setPhoto("/img/default.jpg");
                info.setGender(false);
                info.setPoint(0);
                info.setLevel("一条腿");
                info.setPhone(user.getUserphone());
                info.setReportedTimes(0);
                info.setAddressId(-1);
                info.setCard("");
                String name = RandomUtil.generateRandomDigitString(5);
                int count = 0;
                while (!runDeliveryInfoService.checkNameUnique(name)) {
                    if (count >= 5) {
                        return BaseResult.fail(ResultEnum.Number_THAN_BIG);
                    }
                    name = RandomUtil.generateRandomDigitString(5);
                    count++;
                }
                info.setName("游客_" + name);
                runDeliveryInfoService.saveRunDeliveryInfo(info);
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
     * 验证配送员身份证号格式
     *
     * @param id
     * @param card
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delivery/check/card/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "验证配送员身份证号格式(刘明宇)", notes = "验证配送员身份证号格式", response = BaseResult.class)
    public BaseResult checkCard(@PathVariable Integer id, @RequestParam("card") String card, HttpServletRequest request) throws Exception {
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

    //-------------------------------管理员模块---------------------------------

    /**
     * 添加管理员
     *
     * @param admin
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/admins", method = RequestMethod.POST)
    @ApiOperation(value = "添加管理员(刘明宇)", notes = "添加管理员", response = BaseResult.class)
    public BaseResult insertAdmin(@RequestBody RunAdmin admin, HttpServletRequest request) throws Exception {
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
     */
    @RequestMapping(value = "/admins/login", method = RequestMethod.POST)
    @ApiOperation(value = "管理员登录(刘明宇)", notes = "管理员登录", response = BaseResult.class)
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


    /**
     * 跳到帮我买页面需初始化的数据
     *
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/helpbuy", method = RequestMethod.GET)
    @ApiOperation(value = "跳到帮我买页面需初始化的数据(刘明宇)", notes = "跳到帮我买页面需初始化的数据", response = BaseResult.class)
    public BaseResult toHelpBuy() throws AppException {
        LOGGER.info("{} 跳到帮我买页面", LOG_PREFIX);
        List<String> goods = HelpBuyGoodsEnum.getAllGoods();
        return BaseResult.success(goods);
    }

    /**
     * 跳到帮我送页面需初始化的数据
     *
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/helpsend", method = RequestMethod.GET)
    @ApiOperation(value = "跳到帮我送页面需初始化的数据(刘明宇)", notes = "跳到帮我送页面需初始化的数据", response = BaseResult.class)
    public BaseResult toHelpSend() throws AppException {
        LOGGER.info("{} 跳到帮我送页面", LOG_PREFIX);
        List<String> goods = HelpSendGoodsEnum.getAllGoods();
        return BaseResult.success(goods);
    }

    /**
     * 跳到帮我取页面需初始化的数据
     *
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/helpget", method = RequestMethod.GET)
    @ApiOperation(value = "跳到帮我取页面需初始化的数据(刘明宇)", notes = "跳到帮我取页面需初始化的数据", response = BaseResult.class)
    public BaseResult toHelpGet() throws AppException {
        LOGGER.info("{} 跳到帮我取页面", LOG_PREFIX);
        List<String> goods = HelpSendGoodsEnum.getAllGoods();
        return BaseResult.success(goods);
    }

    /**
     * 跳到代排队页面需初始化的数据
     *
     * @return
     * @throws AppException
     */
    @RequestMapping(value = "/helpqueue", method = RequestMethod.GET)
    @ApiOperation(value = "跳到代排队页面需初始化的数据(刘明宇)", notes = "跳到代排队页面需初始化的数据", response = BaseResult.class)
    public BaseResult toHelpQueue() throws AppException {
        LOGGER.info("{} 跳到代排队页面", LOG_PREFIX);
        List<String> queues = HelpQueueTypeEnum.getAllQueue();
        return BaseResult.success(queues);
    }

}
