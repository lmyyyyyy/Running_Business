package com.running.business.interceptor;

import com.running.business.exception.AppException;
import com.running.business.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * @author liumingyu
 * @create 2018-01-07 下午3:07
 */
@Component
public class PermissionInterceptor {

    public static final String RUN_ADMIN_TOKEN = "RA";

    @Autowired
    private RequestUtil requestUtil;
    /**
     * 验证是否是管理员
     *
     * @param request
     * @return
     * @throws AppException
     */
    public boolean isInvoke(HttpServletRequest request) throws AppException {
        String token = requestUtil.getTokenStr(request);
        if (RUN_ADMIN_TOKEN.equals(token.substring(0, 2))) {
            return true;
        } else {
            return false;
        }
    }
}
