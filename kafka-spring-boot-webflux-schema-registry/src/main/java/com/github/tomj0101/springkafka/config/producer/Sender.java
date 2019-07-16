package com.github.tomj0101.springkafka.config.producer;

import com.github.tomj0101.springkafka.model.avro.gen.ProfileAvro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;


public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${spring.kafka.topic}")
    private String avroTopic;

    @Autowired
    private KafkaTemplate<String, ProfileAvro> kafkaTemplate;

    public void send(ProfileAvro profileAvro) {
        LOGGER.info("sending profileAvro='{}'", profileAvro.toString());
        kafkaTemplate.send(avroTopic, profileAvro);
    }
}
