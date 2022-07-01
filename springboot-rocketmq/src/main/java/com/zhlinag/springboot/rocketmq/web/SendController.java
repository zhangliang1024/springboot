package com.zhlinag.springboot.rocketmq.web;

import com.zhlinag.springboot.rocketmq.general.GeneralProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: SendController
 * Function:
 * Date: 2022年04月20 17:41:35
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SendController {

    private final GeneralProducer generalProducer;

    @GetMapping("send/msg")
    public void sendMsg() throws Exception {
        for (int i = 0; i < 20; i++) {
            Thread.sleep(2000);
            this.generalProducer.sendGeneralMessage();
        }
    }

}
