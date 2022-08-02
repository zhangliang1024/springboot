package com.zhliang.springboot.socket.io.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.Transport;
import com.zhliang.springboot.socket.io.handler.SocketIOHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @ClassNameSocketIOConfig
 * @Description TODO
 * @Author DELL
 * @Date 2022/1/2114:19
 * @Version 1.0
 **/
@Configuration
public class SocketIOConfig implements InitializingBean {

    @Resource
    private SocketIOHandler socketIOHandler;

    @Value("${socketio.host}")
    private String host;

    @Value("${socketio.port}")
    private Integer port;

    @Value("${socketio.bossCount}")
    private int bossCount;

    @Value("${socketio.workCount}")
    private int workCount;

    @Value("${socketio.allowCustomRequests}")
    private boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    private int upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    private int pingTimeout;

    @Value("${socketio.pingInterval}")
    private int pingInterval;


    @Override
    public void afterPropertiesSet() throws Exception {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        configuration.setSocketConfig(socketConfig);
        // host在本地测试可以设置为localhost或者本机IP，在Linux服务器跑可换成服务器IP
        configuration.setHostname(host);
        configuration.setPort(port);
        // socket连接数大小（如只监听一个端口boss线程组为1即可）
        configuration.setBossThreads(bossCount);
        // 连接数大小
        configuration.setWorkerThreads(workCount);
        configuration.setAllowCustomRequests(allowCustomRequests);
        // 协议升级超时时间（毫秒），默认10秒。HTTP握手升级为ws协议超时时间
        configuration.setUpgradeTimeout(upgradeTimeout);
        // Ping消息超时时间（毫秒），默认60秒，这个时间间隔内没有接收到心跳消息就会发送超时事件
        configuration.setPingTimeout(pingTimeout);
        // Ping消息间隔（毫秒），默认25秒。客户端向服务器发送一条心跳消息间隔
        configuration.setPingInterval(pingInterval);

        // 设置是否可以跨域访问
        configuration.setOrigin("*");
        configuration.setTransports(Transport.POLLING, Transport.WEBSOCKET);

        // 鉴权管理 --> SpringBoot OAuth2.0 封装登录、刷新令牌接口
        //configuration.setAuthorizationListener(new AuthorizationListener() {
        //    @Override
        //    public boolean isAuthorized(HandshakeData data) {
                //String accessToken = data.getSingleUrlParam(OAuth2AccessToken.ACCESS_TOKEN);
                //if (StringUtils.isEmpty(accessToken)) {
                //    return false;
                //}
                //OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                //return !ObjectUtils.isEmpty(oAuth2AccessToken);
        //    }
        //});

        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        //添加事件监听器
        socketIOServer.addListeners(socketIOHandler);

        //启动SocketIOServer
        socketIOServer.start();
        System.out.println("SocketIO启动完毕");
    }
}
