package com.zhliang.springboot.socket.io.cache;

import com.corundumstudio.socketio.SocketIOClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassNameClientCache
 * @Description TODO 缓存用户 - 页面sessionId - 通道连接
 * @Author DELL
 * @Date 2022/1/2113:55
 * @Version 1.0
 **/
@Component
public class ClientCache {
    /**
     * @Author 二哈老头子
     * @Description //TODO 用户信息缓存
     * @Date 14:00 2022/1/21
     * @Param
     * @return
     **/
    private static Map<String, HashMap<UUID, SocketIOClient>> concurrentHashMap = new ConcurrentHashMap<>();

    /**
     * @Author 二哈老头子
     * @Description //TODO userId-用户ID | sessionId-页面sessionId | socketIOClient-页面对应的通道连接
     * @Date 14:03 2022/1/21
     * @Param [userId, sessionId, socketIOClient]
     * @return void
     **/
    public void saveClient(String userId,UUID sessionId,SocketIOClient socketIOClient){
        HashMap<UUID, SocketIOClient> sessionIdClientCache = concurrentHashMap.get(userId);
        if(sessionIdClientCache == null){
            sessionIdClientCache = new HashMap<>();
        }
        sessionIdClientCache.put(sessionId,socketIOClient);
        concurrentHashMap.put(userId,sessionIdClientCache);
    }

    /**
     * @Author 二哈老头子
     * @Description //TODO 获取用户的页面通道信息
     * @Date 14:12 2022/1/21
     * @Param [userId]
     * @return java.util.HashMap<java.util.UUID,com.corundumstudio.socketio.SocketIOClient>
     **/
    public HashMap<UUID,SocketIOClient> getUserClient(String userId){
        return concurrentHashMap.get(userId);
    }

    /**
     * @Author 二哈老头子
     * @Description //TODO 根据用户Id及页面sessionID删除页面通道连接
     * @Date 14:14 2022/1/21
     * @Param [userId, sessionId]
     * @return void
     **/
    public void deleteSessionClientByUserId(String userId,UUID sessionId){
        concurrentHashMap.get(userId).remove(sessionId);
    }

    /**
     * @Author 二哈老头子
     * @Description //TODO 根据用户Id删除用户通道连接缓存 暂无使用
     * @Date 14:19 2022/1/21
     * @Param [userId]
     * @return void
     **/
    public void deleteUserCacheByUserId(String userId){
        concurrentHashMap.remove(userId);
    }
}
