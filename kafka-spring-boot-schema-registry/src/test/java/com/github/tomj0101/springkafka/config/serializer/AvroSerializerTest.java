package com.github.tomj0101.springkafka.config.serializer;

import static org.assertj.core.api.Assertions.assertThat;

import javax.xml.bind.DatatypeConverter;

import com.github.tomj0101.springkafka.model.avro.gen.ProfileAvro;
import org.junit.Test;

public class AvroSerializerTest {

    @Test
    public void testSerialize() {
        ProfileAvro profileAvro = ProfileAvro.newBuilder().setName("Tom").setNumber1(1)
                .setNumber2(2.0F).build();

        AvroSerializer<ProfileAvro> avroSerializer = new AvroSerializer<>();
        assertThat(avroSerializer.serialize("topic-example-200", profileAvro))
                .isEqualTo(DatatypeConverter.parseHexBinary("104A6F686E20446F6502000A677265656E"));

        avroSerializer.close();
    }
}
