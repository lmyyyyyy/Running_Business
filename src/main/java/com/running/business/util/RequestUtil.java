package com.running.business.util;

import com.running.business.common.Config;
import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunDeliveryuser;
import com.running.business.pojo.RunUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author liumingyu
 * @create 2017-12-03 下午5:06
 */
public class RequestUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);
    @Autowired
    private JedisClient jedisClient;

    public JedisClient getJedisClient() {
        return jedisClient;
    }

    public void setJedisClient(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }

    /**
     * 根据request 获取token， token获取缓存中的json字符串
     *
     * @param req
     * @return
     */
    public String getToken(HttpServletRequest req) {
        LOGGER.info("Token");

        String token = "";
        Cookie[] cookie = req.getCookies();
        if (cookie == null) {
            UserUtil.unbindUser();
            return null;
        }
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equals(Config.TOKEN_COOKIE)) {
                token = cook.getValue().toString();
            }
        }

        LOGGER.info("访问getToken获取token： " + token);

        if ("".equals(token)) {
            UserUtil.unbindUser();
            return null;
        }

        //根据token获取当前用户实体的json格式字符串
        String key = Config.REDIS_USER_SESSION_KEY + ":" + token;
        String UserLoginSessionStr = jedisClient.get(key);
        LOGGER.info("访问getToken获取用户实体： " + UserLoginSessionStr);

        if (null == UserLoginSessionStr || "".equals(UserLoginSessionStr)) {
            return null;
        } else {
            LOGGER.info("用户Session正常,重写设置失效时间");
            jedisClient.expire(key, Config.SSO_SESSION_EXPIRE);
            LOGGER.info("设置失效时间完成" + Config.SSO_SESSION_EXPIRE);
            return UserLoginSessionStr;
        }
    }

    /**
     * 获取当前请求的用户id
     *
     * @param req
     * @return
     * @throws AppException
     */
    public Integer getUserId(HttpServletRequest req) throws AppException {
        String jsonStr = getToken(req);
        RunUser user = JsonUtils.jsonToPojo(jsonStr, RunUser.class);
        if (user == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        return user.getUid();
    }

    /**
     * 获取当前请求的配送员id
     *
     * @param request
     * @return
     * @throws AppException
     */
    public Integer getDeliveryId(HttpServletRequest request) throws AppException {
        String jsonStr = getToken(request);
        RunDeliveryuser deliveryuser = JsonUtils.jsonToPojo(jsonStr, RunDeliveryuser.class);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        return deliveryuser.getDid();
    }

    /**
     * 获取当前请求的管理员id
     *
     * @param request
     * @return
     * @throws AppException
     */
    public Integer getAdminId(HttpServletRequest request) throws AppException {
        String jsonStr = getToken(request);
        if (jsonStr == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        if (admin == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        return admin.getAdminId();
    }

    /**
     * 根据jsonStr获取用户Id
     *
     * @param jsonStr
     * @return
     * @throws AppException
     */
    public Integer getUserIdByJsonStr(String jsonStr) throws AppException {
        if (jsonStr == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunUser user = JsonUtils.jsonToPojo(jsonStr, RunUser.class);
        if (user == null) {
            throw new AppException(ResultEnum.JSON_TO_ENTITY_ERROR);
        }
        return user.getUid();
    }

    /**
     * 根据jsonStr获取配送员Id
     *
     * @param jsonStr
     * @return
     * @throws AppException
     */
    public Integer getDeliveryIdByJsonStr(String jsonStr) throws AppException {
        if (jsonStr == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunDeliveryuser deliveryuser = JsonUtils.jsonToPojo(jsonStr, RunDeliveryuser.class);
        if (deliveryuser == null) {
            throw new AppException(ResultEnum.JSON_TO_ENTITY_ERROR);
        }
        return deliveryuser.getDid();
    }

    /**
     * 根据jsonStr获取管理员Id
     *
     * @param jsonStr
     * @return
     * @throws AppException
     */
    public Integer getAdminIdByJsonStr(String jsonStr) throws AppException {
        if (jsonStr == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        if (admin == null) {
            throw new AppException(ResultEnum.JSON_TO_ENTITY_ERROR);
        }
        return admin.getAdminId();
    }

    /**
     * 获取token字符串
     *
     * @param req
     * @return
     */
    public String getTokenStr(HttpServletRequest req) throws AppException {
        LOGGER.info("TokenStr");

        String token = "";
        Cookie[] cookie = req.getCookies();
        if (cookie == null) {
            throw new AppException(ResultEnum.COOKIE_IS_OUT_TIME);
        }
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equals(Config.TOKEN_COOKIE)) {
                token = cook.getValue().toString();
            }
        }
        if (token == null || "".equals(token)) {
            throw new AppException(ResultEnum.COOKIE_IS_OUT_TIME);
        }
        return token;
    }

    /**
     * 获取当前用户的ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
