package com.github.tomj0101.springkafka.controller;

import com.github.tomj0101.springkafka.model.ProfileModel;
import com.github.tomj0101.springkafka.service.ProfileServiceKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final ProfileServiceKafka producer;

    @Autowired
    KafkaController(ProfileServiceKafka producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestBody ProfileModel profileModel) {
        this.producer.sendMessage(profileModel);
    }
}
