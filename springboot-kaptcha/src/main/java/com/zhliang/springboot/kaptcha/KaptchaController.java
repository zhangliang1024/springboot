package com.zhliang.springboot.kaptcha;

import com.baomidou.kaptcha.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @项目名称：springboot
 * @包名：com.zhliang.springboot.kaptcha
 * @类描述：
 * @创建人：colin
 * @创建时间：2019/12/4 20:11
 * @version：V1.0
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }

    @PostMapping("/valid")
    public void validDefaultTime(@RequestParam String code) {
        //default timeout 900 seconds
        kaptcha.validate(code);
    }

    @PostMapping("/validTime")
    public void validCustomTime(@RequestParam String code) {
        kaptcha.validate(code, 60);
    }

}
