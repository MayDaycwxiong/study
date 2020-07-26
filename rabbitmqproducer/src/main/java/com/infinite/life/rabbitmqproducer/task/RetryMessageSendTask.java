package com.infinite.life.rabbitmqproducer.task;

import com.infinite.life.common.entity.rabbitmq.SingleDTO;
import com.infinite.life.rabbitmqproducer.producer.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class RetryMessageSendTask {

    @Autowired
    private MessageSender sender;

    @Scheduled(initialDelay = 3000, fixedDelay = 10000)
    public void reSend() {
        log.info("############定时任务开始发送消息############");
        SingleDTO singleDTO = new SingleDTO();
        singleDTO.setUrid("cuiwx-first");
        singleDTO.setEntNum("china");
        singleDTO.setTransNo("first");
        singleDTO.setState("full");
        singleDTO.setInTime(new Date());
        singleDTO.setAmount(6.666);
        singleDTO.setVersion(1);
        singleDTO.setMemo("test");
        sender.messageSender(singleDTO);
    }
}
