package com.infinite.life.rabbitmqproducer.producer

import com.infinite.life.common.entity.rabbitmq.SingleDTO
import org.springframework.amqp.rabbit.core.RabbitTemplate
import spock.lang.Specification

class MessageSenderTest extends Specification {

    MessageSender sender = new MessageSender()
    RabbitTemplate template = Mock()

    void setup() {
        sender.rabbitTemplate = template
    }

    def "test messageSender"() {
        given:
        SingleDTO singleDTO = new SingleDTO(urid: 'cuiwx-first', entNum: 'china', transNo: 'first', state: 'full', inTime: new Date(), amount: 6.666, version: 1, memo: 'test')
        when: "测试消息发送"
        sender.messageSender(singleDTO)
        then:
        1 * template.convertAndSend(*_)
        notThrown(Exception)
    }
}

;
