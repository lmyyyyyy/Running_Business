package com.running.business.interceptor;

import com.running.business.common.Config;
import com.running.business.dto.UserDTO;
import com.running.business.mapper.JedisClient;
import com.running.business.service.SSOService;
import com.running.business.util.UserUtil;
import lombok.Getter;
import lombok.Setter;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginInterceptor.class);

    private static final ThreadLocal<Long> treadLocal = new ThreadLocal<>();
    @Getter
    @Setter
    public String defultLogin = "/index";

    /**
     * 用户token键
     */
    @Value("${REDIS_USER_SESSION_KEY}")
    private String REDIS_USER_SESSION_KEY;
    /**
     * session生命周期
     */
    @Value("${SSO_SESSION_EXPIRE}")
    private String SSO_SESSION_EXPIRE;

    @Autowired
    private JedisClient jedisClient;

    @Autowired
    private SSOService ssoService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOGGER.info("验证TOKEN是否失效Begin...");
        treadLocal.set(System.currentTimeMillis());
        String token = getToken(httpServletRequest);
        String str = getUserJsonStr(token);
        if (str == null || str.equals("")) {
            UserUtil.unbindUser();
            LOGGER.info("--还没登录，跳到登录界面--", o);
            httpServletResponse.sendRedirect(defultLogin);
            return false;
        }
        try {

            UserDTO userDTO = ssoService.getUserDTO(httpServletRequest);
            if (userDTO == null) {
                LOGGER.info("当前线程与用户绑定失败");
            } else {
                LOGGER.info("当前线程与用户进行绑定");
                UserUtil.bind(userDTO);
            }
        } catch (Exception e) {
            LOGGER.error("--当前线程与用户绑定失败 error = {}", e);
            return false;
        }
        LOGGER.info("{} 验证成功,放行", str);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        Long startTime = treadLocal.get();
        if (startTime == null) {
            startTime = System.currentTimeMillis();
        }
        Long endTime = System.currentTimeMillis();
        Long costTime = endTime - startTime;
        LOGGER.info("该请求执行时间为:" + costTime + "毫秒");
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
            UserUtil.unbindUser();
            return null;
        }

        return token;
    }

    public String getUserJsonStr(String token) {
        if (token == null || "".equals(token)) {
            UserUtil.unbindUser();
            return null;
        }

        //根据token获取当前用户实体的json格式字符串
        String key = Config.REDIS_USER_SESSION_KEY + ":" + token;

        String UserLoginSessionStr = jedisClient.get(key);
        LOGGER.info("访问getUserJsonStr获取用户实体： " + UserLoginSessionStr);

        if (null == UserLoginSessionStr || "".equals(UserLoginSessionStr)) {
            return null;
        } else {
            LOGGER.info("用户Session正常,重写设置失效时间");
            jedisClient.expire(key, Config.SSO_SESSION_EXPIRE);
            LOGGER.info("设置失效时间完成" + Config.SSO_SESSION_EXPIRE);
            return UserLoginSessionStr;
        }
    }

}
