package com.zhliang.springboot.socket.io.controller;

import com.corundumstudio.socketio.SocketIOClient;
import com.zhliang.springboot.socket.io.cache.ClientCache;
import com.zhliang.springboot.socket.io.model.MessageInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/push")
public class PushController {
    @Resource
    private ClientCache clientCache;

    @GetMapping("/user/{userId}")
    public String pushTuUser(@PathVariable("userId") String userId){
        HashMap<UUID, SocketIOClient> userClient = clientCache.getUserClient(userId);
        userClient.forEach((uuid, socketIOClient) -> {
            //向客户端推送消息
            socketIOClient.sendEvent("chatevent",new MessageInfo("管理员","向客户段发送的消息"));
        });
        return "success";
    }
}
