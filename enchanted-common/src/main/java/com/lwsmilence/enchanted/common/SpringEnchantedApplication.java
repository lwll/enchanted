package com.lwsmilence.enchanted.common;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
@EnableRabbit
@EnableAspectJAutoProxy
public class SpringEnchantedApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(SpringEnchantedApplication.class);

        // 自定义banner内容
        springApplication.setBanner((environment, sourceClass, out) -> {
            out.println("Enchanted");
        });
        // 设置banner模式
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        springApplication.run(args);
    }

}
