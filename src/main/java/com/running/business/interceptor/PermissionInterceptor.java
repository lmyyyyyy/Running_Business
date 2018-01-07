package com.running.business.interceptor;

import com.running.business.exception.AppException;
import com.running.business.util.RequestUtil;

import javax.servlet.http.HttpServletRequest;


/**
 * @author liumingyu
 * @create 2018-01-07 下午3:07
 */
public class PermissionInterceptor {

    public static final String RUN_ADMIN_TOKEN = "RA";

    /**
     * 验证是否是管理员
     *
     * @param request
     * @return
     * @throws AppException
     */
    public static boolean isInvoke(HttpServletRequest request) throws AppException {
        String token = RequestUtil.getTokenStr(request);
        if (RUN_ADMIN_TOKEN.equals(token.substring(0, 2))) {
            return true;
        } else {
            return false;
        }
    }
}
