package com.infinite.life.rabbitmqproducer.producer;

import com.infinite.life.common.entity.rabbitmq.SingleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cuiwx  2020/7/26
 * @description rabbit消息发送类
 * @return
 */
@Slf4j
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.producer.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.producer.routingKey}")
    private String routingKey;

    /**
     * @description 回调函数:confirm 确认
     * @author cuiwx  2020/7/26
     * @return
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData:[{}]", correlationData);
            if (ack) {
                log.warn("服务器端确认收到消息，消息投递到broker成功");
            } else {
                log.error("投递消息到broker出现异常，进行异常处理...");
            }
        }
    };

    /**
     * @description 回调函数:return 回退
     * @author cuiwx  2020/7/26
     * @return
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String desc, String exchangeName, String routingKey) {
            log.error("消息被退回");
            log.info("message:[{}],replayCode:[{}],desc:[{}],exchangeName:[{]],routingKey:[{]]", message.toString(), replyCode, desc, exchangeName, routingKey);
        }
    };

    public void messageSender(SingleDTO singleDTO) {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(singleDTO.getUrid());
        rabbitTemplate.convertAndSend(exchange,
                routingKey,
                singleDTO,
                correlationData);
    }
}
