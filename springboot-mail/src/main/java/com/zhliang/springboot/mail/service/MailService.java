package com.zhliang.springboot.mail.service;

import com.zhliang.springboot.mail.vo.MailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @Author: colin
 * @Date: 2019/10/14 15:37
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Service
public class MailService {

    @Autowired
    private JavaMailSenderImpl mailSender;//注入邮件工具类


    /**
     * 发送邮件
     */
    public MailVo sendMail(MailVo mailVo) {
        try {
            checkMail(mailVo); //1.检测邮件
            sendMimeMail(mailVo); //2.发送邮件
            return saveMail(mailVo); //3.保存邮件
        } catch (Exception e) {
            log.error("发送邮件失败:", e);//打印错误信息
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }

    }


    //检测邮件信息类
    private void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    //构建复杂邮件信息类
    private void sendMimeMail(MailVo mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
            mailVo.setFrom(getMailSendFrom());//邮件发信人从配置项读取
            messageHelper.setFrom(mailVo.getFrom());//邮件发信人
            messageHelper.setTo(mailVo.getTo().split(","));//邮件收信人
            messageHelper.setSubject(mailVo.getSubject());//邮件主题
            messageHelper.setText(mailVo.getText());//邮件内容
            if (!StringUtils.isEmpty(mailVo.getCc())) {//抄送
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mailVo.getBcc())) {//密送
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {//添加邮件附件
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (StringUtils.isEmpty(mailVo.getSentDate())) {//发送时间
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            mailVo.setStatus("ok");
            log.info("发送邮件成功：{}->{}", mailVo.getFrom(), mailVo.getTo());
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }

    //保存邮件
    private MailVo saveMail(MailVo mailVo) {

        //将邮件保存到数据库..

        return mailVo;
    }

    //获取邮件发信人
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }
}
