package com.lwsmilence.enchanted.common;

import com.lwsmilence.enchanted.common.aop.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class SpringEnchantedApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    UserService userService;

    @Test
    void testDirectExchange() {
        for (int i = 0; i < 200; i++) {
            rabbitTemplate.convertAndSend( "book.direct", "book","book" + i);
        }
        log.info("消息发送到rabbitmq成功");
    }

    @Test
    void testAOP() {
        userService.execute();
    }

}
