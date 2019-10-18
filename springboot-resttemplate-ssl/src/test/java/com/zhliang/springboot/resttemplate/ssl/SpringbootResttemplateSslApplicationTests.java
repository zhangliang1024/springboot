package com.zhliang.springboot.resttemplate.ssl;

import com.zhliang.springboot.resttemplate.ssl.domain.dto.Mail;
import com.zhliang.springboot.resttemplate.ssl.service.MailSendService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootResttemplateSslApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailSendService mailSendService;

    @Test
    public void test1() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        /**
         * ★ 不加setFrom() 报错：
         * - Caused by: com.sun.mail.smtp.SMTPSenderFailedException: 501 mail from address must be same as authorization user
         */
        simpleMailMessage.setFrom("493478910@qq.com");
        // 邮件主题
        simpleMailMessage.setSubject("测试");
        // 邮件接收人邮箱
        simpleMailMessage.setTo("493478910@qq.com");
        // 邮件内容
        simpleMailMessage.setText("这是一封测试邮件");
        javaMailSender.send(simpleMailMessage);
    }

    @Test
    public void test2() {
        Map<String, Object> params = new HashMap<>();
        params.put("company", "a公司");
        params.put("contact", "b联系方式");
        params.put("email", "c询问下单邮件");
        params.put("fax", "d传真");
        params.put("more", "e备注");
        Mail mail = new Mail();
        // 设置接受方邮箱
        mail.setReceiverEmail("493478910@qq.com");
        // 使用的模板名称
        mail.setTemplateName("place_order.ftl");
        // 设置
        mail.setParams(params);
        mail.setSubject("主题:测试邮件");
        boolean isSucc = mailSendService.sendWithHTMLTemplate(mail);
    }


}
