package com.github.tomj0101.springkafka.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerService {
    private final Logger logger = LoggerFactory.getLogger(ProducerService.class);

//    @KafkaListener(topics = "topic-example-100", groupId = "group_id")
//    public void consume(String message) throws IOException {
//        logger.info(String.format("#### -> Consumed message -> %s", message));
//    }
}
