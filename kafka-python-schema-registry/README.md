```
pip install confluent-kafka[avro]
```


```
// Register new schema
curl -vs --stderr - -XPOST -i -H "Content-Type: application/vnd.schemaregistry.v1+json" --data '{"schema":"\"{\n \\"type\\": \\"record\\",\n \\"name\\": \\"evolution\\",\n \\"doc\\": \\"This is a sample Avro schema to get you started. Please edit\\",\n \\"namespace\\": \\"com.landoop\\",\n \\"fields\\": [\n {\n \\"name\\": \\"name\\",\n \\"type\\": \\"string\\"\n },\n {\n \\"name\\": \\"number1\\",\n \\"type\\": \\"int\\"\n },\n {\n \\"name\\": \\"number2\\",\n \\"type\\": \\"float\\"\n }\n ]\n}\""}' /api/schema-registry/subjects/topic-example-avro/versions
```