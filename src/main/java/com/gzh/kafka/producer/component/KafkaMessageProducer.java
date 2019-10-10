package com.gzh.kafka.producer.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import lombok.extern.slf4j.Slf4j;

@Component
//@EnableScheduling
@Slf4j
public class KafkaMessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.app.topic.foo}")
    private String topic;

    //@Scheduled(cron = "00/5 * * * * ?")
    public void send() {
        String message = "Hello World---" + System.currentTimeMillis();
        log.info("topic="+topic+",message="+message);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);
        future.addCallback(success -> log.info("KafkaMessageProducer 发送消息成功！"),
                fail -> log.error("KafkaMessageProducer 发送消息失败！"));
    }
}