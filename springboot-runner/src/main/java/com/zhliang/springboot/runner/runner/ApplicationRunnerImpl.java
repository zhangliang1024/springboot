package com.zhliang.springboot.runner.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/11 15:19
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println("通过实现ApplicationRunner接口，在spring boot项目启动后打印参数");
        String[] sourceArgs = args.getSourceArgs();
        for (String arg : sourceArgs) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}
