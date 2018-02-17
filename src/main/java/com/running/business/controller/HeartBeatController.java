package com.running.business.controller;

import com.running.business.common.BaseResult;
import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.dto.UserDTO;
import com.running.business.enums.UserTypeEnum;
import com.running.business.pojo.HeartBeat;
import com.running.business.service.HeartBeatService;
import com.running.business.service.SSOService;
import com.running.business.util.RequestUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jsoup.Connection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author liumingyu
 * @create 2018-01-13 下午6:38
 */
@RestController
@RequestMapping("/heartbeat")
@Api(value = "心跳检测模块", tags = {"心跳检测模块"})
public class HeartBeatController extends BaseController {

    private Logger LOGGER = LoggerFactory.getLogger(HeartBeatController.class);

    private static final String LOG_PREFIX = "【心跳检测】 ";

    @Autowired
    private RequestUtil requestUtil;

    @Autowired
    private SSOService ssoService;

    @Autowired
    private HeartBeatService heartBeatService;

    /**
     * 用户心跳检测
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ApiOperation(value = "用户心跳检测(刘明宇)", notes = "心跳检测", response = BaseResult.class)
    public BaseResult heartBeat(HttpServletRequest request) throws Exception {
        String token = requestUtil.getTokenStr(request);
        if (token == null || "".equals(token)) {
            LOGGER.error("{} token失效", LOG_PREFIX);
            return BaseResult.fail(ResultEnum.COOKIE_IS_OUT_TIME);
        }
        String jsonStr = requestUtil.getToken(request);
        if (jsonStr == null || "".equals(jsonStr)) {
            return BaseResult.fail(ResultEnum.SESSION_IS_OUT_TIME);
        }
        String ip = RequestUtil.getIpAddr(request);
        String head = token.substring(0, 2);
        HeartBeat heartBeat = new HeartBeat();
        Integer id;
        if (Config.DELIVERY_TOKEN_HEAD.equals(head)) {
            id = requestUtil.getDeliveryIdByJsonStr(jsonStr);
            heartBeat.setUserType(UserTypeEnum.DELIVERY.getCode());
        } else if (Config.USER_TOKEN_HEAD.equals(head)) {
            id = requestUtil.getUserIdByJsonStr(jsonStr);
            heartBeat.setUserType(UserTypeEnum.USER.getCode());
        } else if (Config.ADMIN_TOKEN_HEAD.equals(head)) {
            id = requestUtil.getAdminIdByJsonStr(jsonStr);
            heartBeat.setUserType(UserTypeEnum.ADMIN.getCode());
        } else {
            LOGGER.error("{} token获取异常,不知名的token", LOG_PREFIX);
            return BaseResult.fail(ResultEnum.NOT_NAME_TOKEN);
        }
        LOGGER.info("{} 当前是{}的心跳检测 ip = {}", LOG_PREFIX, id, ip);

        heartBeat.setSessionKey(token);
        heartBeat.setUid(id);
        heartBeat.setUserInfo(jsonStr);
        heartBeat.setIp(ip);
        HeartBeat beat = heartBeatService.save(heartBeat);
        return BaseResult.success(beat);
    }

}
