package com.running.business.interceptor;

import com.running.business.mapper.JedisClient;
import com.running.business.service.RunUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liumingyu
 * @create 2017-11-28 下午2:59
 */
public class LoginInterceptor implements HandlerInterceptor {

    private Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 用户token键
     */
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    /**
     * session生命周期
     */
    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Autowired
    RunUserService runUserService;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String str = getToken(httpServletRequest);
        if (str == null || str.equals("")) {
            LOGGER.info("还没登录，调到登录界面",o);
            httpServletResponse.sendRedirect("/tologin");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public String getToken(HttpServletRequest req) {
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

}
