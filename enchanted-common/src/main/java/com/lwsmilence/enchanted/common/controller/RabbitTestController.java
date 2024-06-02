package com.lwsmilence.enchanted.common.controller;

import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rabbit-test")
@Slf4j
public class RabbitTestController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/push")
    public void pushDirect() {
        for (int i = 0; i < 200; i++) {
            rabbitTemplate.convertAndSend("book.direct", "book", "book" + i, message -> {
                MessageProperties properties = message.getMessageProperties();
                properties.setCorrelationId(UUID.fastUUID().toString());
                return message;
            });
        }
        log.info("消息发送到rabbitmq成功");
    }
}
