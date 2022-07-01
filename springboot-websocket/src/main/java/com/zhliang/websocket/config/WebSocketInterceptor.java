package com.zhliang.websocket.config;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * ClassName: WebSocketInterceptor
 * Function: 拦截器
 * Date: 2022年03月08 10:35:37
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
public class WebSocketInterceptor implements HandshakeInterceptor {


    /**
     * handler处理前调用，attributes属性最终在WebSocketSession里，
     *   通过webSocketSession。getAuttributes().get(key)获得
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler webSocketHandler, Map<String, Object> attributes) throws Exception {
        if( request instanceof ServletServerHttpRequest){
            ServletServerHttpRequest httpRequest = (ServletServerHttpRequest) request;
            String user = httpRequest.getServletRequest().getParameter("user");
            attributes.put("user",user);
            return true;
        }

        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {

    }
}
