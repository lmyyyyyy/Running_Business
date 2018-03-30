package com.running.business.websocket;

import com.running.business.interceptor.HandShakeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author liumingyu
 * @create 2018-02-02 下午5:18
 */
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(),"/websocket/socketServer.do").addInterceptors(myInterceptor());
        registry.addHandler(systemWebSocketHandler(), "/sockjs/socketServer.do").addInterceptors(myInterceptor()).withSockJS();
    }

    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }

    @Bean
    public HandShakeInterceptor myInterceptor() {
        return new HandShakeInterceptor();
    }
}
