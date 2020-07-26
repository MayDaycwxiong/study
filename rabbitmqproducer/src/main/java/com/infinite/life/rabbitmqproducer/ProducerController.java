package com.infinite.life.rabbitmqproducer;

import com.infinite.life.common.entity.rabbitmq.SingleDTO;
import com.infinite.life.rabbitmqproducer.producer.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ProducerController {

    @Autowired
    private MessageSender sender;

    @RequestMapping("/producer")
    public void producer() {
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
