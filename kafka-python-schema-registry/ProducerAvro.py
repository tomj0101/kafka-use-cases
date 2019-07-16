from confluent_kafka import Producer
import avro.schema
import avro.io
import io
import random

if __name__ == "__main__":

    conf = {'bootstrap.servers': 'localhost:9092'}
    producer = Producer(**conf)

    # Kafka topic
    topic = "topic-example-200"

    # Path to profile.avsc avro schema
    schema_path = "profile.avsc"
    schema = avro.schema.Parse(open(schema_path).read())

    for i in range(10):
        writer = avro.io.DatumWriter(schema)
        bytes_writer = io.BytesIO()
        encoder = avro.io.BinaryEncoder(bytes_writer)
        writer.write({"name": "tom",
                      "number1": random.randint(0, 10),
                      "number2": 2.0}, encoder)
        raw_bytes = bytes_writer.getvalue()
        producer.produce(topic, raw_bytes)
    producer.flush()