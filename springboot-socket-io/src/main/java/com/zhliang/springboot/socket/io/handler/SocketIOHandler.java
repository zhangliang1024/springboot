package com.zhliang.springboot.socket.io.handler;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.zhliang.springboot.socket.io.cache.ClientCache;
import com.zhliang.springboot.socket.io.model.MessageInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * @ClassNameSocketIOHandler
 * @Description TODO
 * @Author DELL
 * @Date 2022/1/2114:29
 * @Version 1.0
 **/
@Component
public class SocketIOHandler {
    @Resource
    private ClientCache clientCache;

    /**
     * @Author 二哈老头子
     * @Description //TODO 客户端连接的时候触发，前端js触发：socket = io.connect("http://localhost:9092");
     * @Date 12:12 2022/1/20
     * @Param [client]
     * @return void
     **/
    @OnConnect
    public void onConnect(SocketIOClient client){
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        UUID sessionId = client.getSessionId();
        clientCache.saveClient(userId,sessionId, client);
        System.out.println("userId: "+userId+"连接建立成功 - "+sessionId);

        client.sendEvent("message", new AckCallback<Object>(null) {
            @Override
            public void onSuccess(Object object) {

            }
        }, "已连接");
    }

    /**
     * @Author 二哈老头子
     * @Description //TODO 客户端关闭连接时触发：前端js触发：socket.disconnect();
     * @Date 12:14 2022/1/20
     * @Param [client]
     * @return void
     **/
    @OnDisconnect
    public void onDisconnect(SocketIOClient client){
        String userId = client.getHandshakeData().getSingleUrlParam("userId");
        UUID sessionId = client.getSessionId();
        clientCache.deleteSessionClientByUserId(userId,sessionId);
        System.out.println("userId: "+userId+"连接关闭成功 - "+sessionId);
    }

    /**
     * @Author 二哈老头子
     * @Description
     *  //TODO 自定义消息事件，客户端js触发:socket.emit('messageevent', {msgContent: msg});时触发该方法
     *  //TODO 前端js的 socket.emit("事件名","参数数据")方法，是触发后端自定义消息事件的时候使用的
     *  //TODO 前端js的 socket.on("事件名",匿名函数(服务器向客户端发送的数据))为监听服务器端的事件
     * @Date 13:51 2022/1/20
     * @Param [client, request, data]
     * @return void
     **/
    @OnEvent("chatevent")
    public void chatEvent(SocketIOClient client, AckRequest ackRequest, MessageInfo message){
        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient("79");
        Iterator<Map.Entry<UUID, SocketIOClient>> iterator = userClient.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<UUID, SocketIOClient> next = iterator.next();
            next.getValue().sendEvent("chatevent", message);
        }
        ackRequest.sendAckData("ping");
        System.out.println(message);
    }
}
