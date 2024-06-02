package com.lwsmilence.enchanted.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class RabbitMQConfig {


//    @Autowired
//    RabbitAdmin rabbitAdmin;
//
//    @Bean
//    public RabbitAdmin rabbitAdmin() {
//        return new RabbitAdmin(rabbitTemplate);
//    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding(StandardCharsets.UTF_8.name());
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback((returnedMessage -> {
            String correlationId = returnedMessage.getMessage().getMessageProperties().getCorrelationId();
            log.error("消息：{} 发送失败，应答码：{} 原因：{} 交换机：{} 路由键：{}", correlationId, returnedMessage.getReplyCode(), returnedMessage.getReplyText(), returnedMessage.getExchange(), returnedMessage.getRoutingKey());
        }));

        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.debug("消息发送到exchange成功");
            } else {
                log.debug("消息发送到exchange失败，原因：{}", cause);
            }
        });

        return rabbitTemplate;
    }

    @Bean
    public Queue bookQueue() {
        return new Queue("bookQueue");
    }

    @Bean
    public Exchange bookExchange() {
        return new DirectExchange("book.direct");
    }

    @Bean
    public Binding bookBinding() {
        return new Binding("bookQueue", Binding.DestinationType.QUEUE, "book.direct", "book", null);
    }

}
