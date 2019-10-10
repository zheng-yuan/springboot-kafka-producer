package com.gzh.kafka.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaMessageSendService {

    
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    
    @Value("${kafka.app.topic.foo}")
    private String topic;
    
    public void send(String message){
        log.info("topic="+topic+",message="+message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success -> log.info("KafkaMessageProducer 发送消息成功！"),
                fail -> log.error("KafkaMessageProducer 发送消息失败！"));
    }
}