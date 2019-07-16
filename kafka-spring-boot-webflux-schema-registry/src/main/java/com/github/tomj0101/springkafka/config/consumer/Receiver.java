package com.github.tomj0101.springkafka.config.consumer;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }


    //comment for shutdown consumer
    /*
    @KafkaListener(topics = "${spring.kafka.topic}")
    public void receive(ProfileAvro profileAvro) {
        LOGGER.info("received profileAvro='{}'", profileAvro.toString());
        latch.countDown();
    }
    */

}
