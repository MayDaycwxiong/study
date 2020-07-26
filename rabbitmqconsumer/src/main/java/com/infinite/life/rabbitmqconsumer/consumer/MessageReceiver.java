package com.infinite.life.rabbitmqconsumer.consumer;

import com.infinite.life.common.entity.rabbitmq.SingleDTO;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author cuiwx
 * @version 1.0  2020/7/26
 * @description rabbit 消息接收类
 */
@Slf4j
@Component
public class MessageReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.first.queue.name}", durable = "${spring.rabbitmq.listener.first.queue.durable}"),
            exchange = @Exchange(value = "${spring.rabbitmq.listener.first.exchange.name}", type = "${spring.rabbitmq.listener.first.exchange.type}"),
            key = "${spring.rabbitmq.listener.first.routing.key}"
    ))
    @RabbitHandler
    public void onMessageReceiver(@Payload SingleDTO singleDTO,
                                  @Headers Map<String, Object> headers,
                                  Channel channel) throws Exception {
        log.info("消费端接收到消息，内容为:[{}]", singleDTO.toString());
        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // ACK
        channel.basicAck(deliveryTag, false);
    }
}
