package com.zhliang.springboot.resttemplate.ssl.controller;

import com.zhliang.springboot.resttemplate.ssl.domain.dto.Mail;
import com.zhliang.springboot.resttemplate.ssl.domain.vo.ReqIntention;
import com.zhliang.springboot.resttemplate.ssl.service.MailSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: colin
 * @Date: 2019/10/14 14:16
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private MailSendService mailSendService;

    /**
     * 通知邮件
     */
    private String notifyEmail = "1751632917@qq.com";

    @PostMapping("/send")
    public String sendEmail(@RequestBody ReqIntention reqIntention) {
        log.info("邮件发送请求, 参数reqIntention={}", reqIntention.toString());

        long startTime = System.currentTimeMillis();
        Map<String, Object> params = new HashMap<>();
        params.put("company", reqIntention.getCompany());
        params.put("contact", reqIntention.getContact());
        params.put("email", reqIntention.getEmail());
        params.put("fax", reqIntention.getFax());
        params.put("more", reqIntention.getMore());
        Mail mail = new Mail();
        mail.setReceiverEmail(notifyEmail);
        mail.setParams(params);
        mail.setTemplateName("place_order.ftl");
        mail.setSubject("主题:测试邮件");
        boolean isSucc = mailSendService.sendWithHTMLTemplate(mail);

        long endTime = System.currentTimeMillis();
        if (isSucc) {
            return("成功发送邮件, 用时: " + (endTime - startTime)/1000.0 + "ms");
        } else {
            return("发送邮件失败");
        }
    }
}
