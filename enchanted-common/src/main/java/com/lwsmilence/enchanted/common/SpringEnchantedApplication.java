package com.lwsmilence.enchanted.common;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class SpringEnchantedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEnchantedApplication.class, args);
    }

}
