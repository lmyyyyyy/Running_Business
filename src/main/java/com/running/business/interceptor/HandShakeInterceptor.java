package com.running.business.interceptor;

import com.running.business.common.Config;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * websocket握手拦截器
 *
 * @author liumingyu
 * @create 2018-02-02 下午5:40
 */
public class HandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 初次握手访问前
     *
     * @param request
     * @param response
     * @param handler
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler handler, Map<String, Object> map) throws Exception {
        System.out.println("Before Handshake");
        if(request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest)request;
            HttpSession session = servletServerHttpRequest.getServletRequest().getSession(false);
            if (session != null) {
                String userName = (String) session.getAttribute(Config.SESSION_USERNAME);
                if (userName == null) {
                    userName = "default-system";
                }
                System.out.println("session username :" + userName);
                map.put(Config.WEBSOCKET_USERNAME, userName);
            }
        }
        return super.beforeHandshake(request, response, handler, map);
    }

    /**
     * 初次握手访问后
     *
     * @param serverHttpRequest
     * @param serverHttpResponse
     * @param webSocketHandler
     * @param e
     */
    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
        super.afterHandshake(serverHttpRequest, serverHttpResponse, webSocketHandler, e);
    }
}
