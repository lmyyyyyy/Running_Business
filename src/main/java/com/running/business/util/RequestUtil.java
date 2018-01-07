package com.running.business.util;

import com.running.business.common.ResultEnum;
import com.running.business.exception.AppException;
import com.running.business.mapper.JedisClient;
import com.running.business.pojo.RunAdmin;
import com.running.business.pojo.RunUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;

/**
 * @author liumingyu
 * @create 2017-12-03 下午5:06
 */
public class RequestUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(RequestUtil.class);

    /**
     * 用户token键
     */
    @Value("${REDIS_USER_SESSION_KEY}")
    private static String REDIS_USER_SESSION_KEY;
    /**
     * session生命周期
     */
    @Value("${SSO_SESSION_EXPIRE}")
    private static Integer SSO_SESSION_EXPIRE;

    @Autowired
    private static JedisClient jedisClient;

    /**
     * 根据request 获取token， token获取缓存中的json字符串
     *
     * @param req
     * @return
     */
    public static String getToken(HttpServletRequest req) {
        LOGGER.info("Token");

        String token = "";
        Cookie[] cookie = req.getCookies();
        if (cookie == null) {
            return null;
        }
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equals("RUN_TOKEN")) {
                token = cook.getValue().toString();
            }
        }

        LOGGER.info("访问getToken获取token： " + token);

        if ("".equals(token)) {
            return null;
        }

        //根据token获取当前用户实体的json格式字符串
        String UserLoginSessionStr = jedisClient.get(REDIS_USER_SESSION_KEY + ":" + token);

        LOGGER.info("访问getToken获取用户实体： " + UserLoginSessionStr);

        if (null == UserLoginSessionStr || "".equals(UserLoginSessionStr)) {
            return null;
        } else {
            jedisClient.expire(REDIS_USER_SESSION_KEY + ":" + token, SSO_SESSION_EXPIRE);
            return UserLoginSessionStr;
        }
    }

    public static Integer getUserId(HttpServletRequest req) throws AppException {
        String jsonStr = getToken(req);
        RunUser user = JsonUtils.jsonToPojo(jsonStr, RunUser.class);
        if (user == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        return user.getUid();
    }

    public static Integer getAdminId(HttpServletRequest request) throws AppException {
        String jsonStr = getToken(request);
        RunAdmin admin = JsonUtils.jsonToPojo(jsonStr, RunAdmin.class);
        if (admin == null) {
            throw new AppException(ResultEnum.SESSION_IS_OUT_TIME);
        }
        return admin.getAdminId();
    }

    /**
     * 获取token字符串
     *
     * @param req
     * @return
     */
    public static String getTokenStr(HttpServletRequest req) throws AppException{
        LOGGER.info("TokenStr");

        String token = "";
        Cookie[] cookie = req.getCookies();
        if (cookie == null) {
            throw new AppException(ResultEnum.COOKIE_IS_OUT_TIME);
        }
        for (int i = 0; i < cookie.length; i++) {
            Cookie cook = cookie[i];
            if (cook.getName().equals("RUN_TOKEN")) {
                token = cook.getValue().toString();
            }
        }
        if (token == null || "".equals(token)) {
            throw new AppException(ResultEnum.COOKIE_IS_OUT_TIME);
        }
        return token;
    }
}
