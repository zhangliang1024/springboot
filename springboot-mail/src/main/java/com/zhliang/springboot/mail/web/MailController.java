package com.zhliang.springboot.mail.web;

import com.zhliang.springboot.mail.service.MailService;
import com.zhliang.springboot.mail.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: colin
 * @Date: 2019/10/14 15:39
 * @Description:
 * @Version: V1.0
 */
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * 发送邮件的主界面
     */
    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("mail/sendMail");//打开发送邮件的页面
        mv.addObject("from", mailService.getMailSendFrom());//邮件发信人
        return mv;
    }


    /**
     * 发送邮件
     */
    @PostMapping("/mail/send")
    public MailVo sendMail(MailVo mailVo, MultipartFile[] files) {
        mailVo.setMultipartFiles(files);
        return mailService.sendMail(mailVo);
    }
}
