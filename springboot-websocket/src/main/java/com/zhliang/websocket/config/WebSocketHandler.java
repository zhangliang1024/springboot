package com.zhliang.websocket.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WebSocketHandler
 * Function: 处理器
 * Date: 2022年03月08 10:17:02
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Slf4j
@Component
public class WebSocketHandler extends AbstractWebSocketHandler {


    /**
     * 存储sessionId和webSocketSession
     * 需要注意的是，websocketSession没有提供无惨构造，不能进行序列化，也就不能通过redis存储
     * 在分布式系统中，要想别的办法实现webSocketSession共享
     */
    private static Map<String, WebSocketSession> sessionMap = new HashMap<>();
    private static Map<String, String> userMap = new HashMap<>();

    /**
     * 获取sessionId
     */
    private String getSessionid(WebSocketSession session) {
        if (session instanceof WebSocketServerSockJsSession) {
            //sock js 连接
            try {
                return ((WebSocketSession) FieldUtils.readField(session, "webSocketSession", true)).getId();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return session.getId();
    }


    /**
     * webSocket连接创建后调用
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 获取参数
        String user = String.valueOf(session.getAttributes().get("user"));
        String sessionid = getSessionid(session);

        userMap.put(user, sessionid);
        sessionMap.put(sessionid, session);
    }


    /**
     * 接收到消息会调用
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        if (message instanceof TextMessage) {

        } else if (message instanceof BinaryMessage) {

        } else if (message instanceof PongMessage) {

        } else {
            log.error("Unexpected WebSocket message Type：[{}]", message);
        }
    }


    /**
     * 连接出错会调用
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        sessionMap.remove(getSessionid(session));
    }

    /**
     * 连接关闭会调用
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessionMap.remove(getSessionid(session));
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    public void sendMessage(String user, String message) {
        String sessionId = userMap.get(user);
        WebSocketSession session = sessionMap.get(sessionId);
        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            log.error("SendMessage error: [{}]", e.getMessage(), e);
        }
    }
}
