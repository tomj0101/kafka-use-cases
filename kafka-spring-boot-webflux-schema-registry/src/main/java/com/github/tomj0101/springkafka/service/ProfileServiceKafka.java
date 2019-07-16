package com.github.tomj0101.springkafka.service;
import com.github.tomj0101.springkafka.model.ProfileModel;
import com.github.tomj0101.springkafka.model.avro.gen.ProfileAvro;
import com.github.tomj0101.springkafka.config.producer.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceKafka {

    @Autowired
    private Sender sender;

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceKafka.class);

    public void sendMessage(ProfileModel profileModel) {

        ProfileAvro profileAvro = ProfileAvro.newBuilder().setName(profileModel.getName()).setNumber1(profileModel.getNumber1())
                .setNumber2(profileModel.getNumber2()).build();
        sender.send(profileAvro);
        logger.info(String.format("#### -> Model message profileModel-> %s", profileModel));
        logger.info(String.format("#### -> Producing message profileAvro-> %s", profileAvro));

    }
}
