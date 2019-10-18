package com.zhliang.springboot.runner.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: colin
 * @Date: 2019/10/11 15:21
 * @Description:
 * @Version: V1.0
 */
@Slf4j
@Component
@Order(1)
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("通过实现CommandLineRunner接口，在spring boot项目启动后打印参数");
        for (String arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }
}
