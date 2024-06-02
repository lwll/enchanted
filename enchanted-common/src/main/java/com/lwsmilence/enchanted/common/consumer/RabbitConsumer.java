package com.lwsmilence.enchanted.common.consumer;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues = "bookQueue")
    public void bookListener1(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("listener1 receive a message: {}", new String(message.getBody()));
    }

}
