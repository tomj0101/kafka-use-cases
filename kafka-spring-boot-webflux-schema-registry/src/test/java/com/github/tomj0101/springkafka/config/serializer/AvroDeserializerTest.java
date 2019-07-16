package com.github.tomj0101.springkafka.config.serializer;

import com.github.tomj0101.springkafka.model.avro.gen.ProfileAvro;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.bind.DatatypeConverter;

public class AvroDeserializerTest {

    @Test
    public void testDeserialize() {
        ProfileAvro profileAvro = ProfileAvro.newBuilder().setName("Tom").setNumber1(1).setNumber2(2.0F).build();

        byte[] data = DatatypeConverter.parseHexBinary("104A6F686E20446F6502000A677265656E");

        AvroDeserializer<ProfileAvro> avroDeserializer = new AvroDeserializer<>(ProfileAvro.class);

        assertThat(avroDeserializer.deserialize("topic-example-200", data)).isEqualTo(profileAvro);
        avroDeserializer.close();
    }
}
