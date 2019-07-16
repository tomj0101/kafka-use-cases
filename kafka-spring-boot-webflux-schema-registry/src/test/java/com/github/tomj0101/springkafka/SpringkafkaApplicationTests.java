package com.github.tomj0101.springkafka;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.github.tomj0101.springkafka.config.consumer.Receiver;
import com.github.tomj0101.springkafka.model.avro.gen.ProfileAvro;
import com.github.tomj0101.springkafka.config.producer.Sender;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringkafkaApplicationTests {

	@Autowired
	private Sender sender;

	@Autowired
	private Receiver receiver;

	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, "topic-example-200");

	@Before
	public void setUp() throws Exception {
		// wait until the partitions are assigned
		for (MessageListenerContainer messageListenerContainer : kafkaListenerEndpointRegistry
				.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(messageListenerContainer,
					embeddedKafka.getPartitionsPerTopic());
		}
	}

	@Test
	public void testReceiver() throws Exception {
		ProfileAvro profileAvro = ProfileAvro.newBuilder().setName("Tom").setNumber1(1)
				.setNumber2(2.0F).build();
		sender.send(profileAvro);

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

}
