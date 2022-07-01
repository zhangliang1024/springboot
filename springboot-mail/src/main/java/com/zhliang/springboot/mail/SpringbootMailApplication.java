package com.zhliang.springboot.mail;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 发送邮件和附件（实用版）:
 *  - https://www.jianshu.com/p/5eb000544dd7
 *  - https://github.com/yizhiwazi/springboot-socks
 */
@SpringBootApplication
public class SpringbootMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMailApplication.class, args);
        //DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4FzkuGWDyv9zgdNbBCuM", "5E6yHHrpAo7LFEInNEtj5e3tzmmWYN");
        //IAcsClient client = new DefaultAcsClient(profile);
        //
        //CommonRequest request = new CommonRequest();
        //request.setSysMethod(MethodType.POST);
        //request.setSysDomain("dysmsapi.aliyuncs.com");
        //request.setSysVersion("2017-05-25");
        //request.setSysAction("SendSms");
        //request.putQueryParameter("RegionId", "cn-hangzhou");
        //request.putQueryParameter("PhoneNumbers", "18734800149");
        //request.putQueryParameter("SignName", "fdafa");
        //request.putQueryParameter("TemplateCode", "fdafafd");
        //try {
        //    CommonResponse response = client.getCommonResponse(request);
        //    System.out.println(response.getData());
        //} catch (ServerException e) {
        //    e.printStackTrace();
        //} catch (ClientException e) {
        //    e.printStackTrace();
        //}
    }

}
