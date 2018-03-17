package com.running.business.websocket;

import com.running.business.common.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liumingyu
 * @create 2018-02-02 下午5:22
 */
public class SystemWebSocketHandler extends TextWebSocketHandler {

    private Logger log = LoggerFactory.getLogger(SystemWebSocketHandler.class);

    private static final ArrayList<WebSocketSession> users;

    static {
        users = new ArrayList<>();
    }

    public SystemWebSocketHandler() {

    }

    /**
     * 初次连接成功执行
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ConnectionEstablished");
        log.debug("ConnectionEstablished");
        users.add(session);
        System.out.println("session principal name: " + session.getPrincipal().getName());
        System.out.println("connect to the websocket success......当前数量:" + users.size());
        session.sendMessage(new TextMessage("connect"));
        session.sendMessage(new TextMessage("new_msg"));

    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
    }

    /**
     * 接收消息处理消息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("handleMessage" + message.toString());
        log.debug("handleMessage" + message.toString());
        //sendMessageToUsers();
        session.sendMessage(new TextMessage(new Date() + ""));
    }

    /**
     * 抛出异常时处理
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        users.remove(session);

        log.debug("handleTransportError" + exception.getMessage());
    }

    /**
     * 连接关闭后处理
     *
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        log.debug("websocket connection closed......");
        String username = (String) session.getAttributes().get(Config.WEBSOCKET_USERNAME);
        System.out.println("用户" + username + "已退出！");
        users.remove(session);
        System.out.println("剩余在线用户" + users.size());

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            System.out.println("websocket : " + user.getAttributes().get(Config.WEBSOCKET_USERNAME));
            if (user.getAttributes().get(Config.WEBSOCKET_USERNAME).equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            System.out.println("websocket : " + user.getAttributes().get(Config.WEBSOCKET_USERNAME));
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 给指定用户集合发送指定消息
     *
     * @param phones
     * @param message
     */
    public void sendMessage2UserList(List<String> phones, TextMessage message) {
        for (String phone : phones) {
            if (phone == null || "".equals(phone.trim())) {
                continue;
            }
            this.sendMessageToUser(phone, message);
        }
    }

}
