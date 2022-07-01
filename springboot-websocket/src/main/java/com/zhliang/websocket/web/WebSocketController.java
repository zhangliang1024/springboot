package com.zhliang.websocket.web;

import com.zhliang.websocket.config.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: WebSocketController
 * Function:
 * Date: 2022年03月08 10:45:39
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@RestController
public class WebSocketController {


    @Autowired
    WebSocketHandler webSocketHandler;

    @GetMapping("api/socket/push")
    public Map pushToWeb(@RequestParam String user, @RequestParam String message) {
        webSocketHandler.sendMessage(user, message);

        Map result = new HashMap<>();
        result.put("user", user);
        result.put("message", message);
        return result;
    }

}
