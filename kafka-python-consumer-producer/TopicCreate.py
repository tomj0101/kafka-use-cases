import confluent_kafka.admin, pprint
conf        = {'bootstrap.servers': 'localhost:9092'}
kafka_admin = confluent_kafka.admin.AdminClient(conf)
new_topic   = confluent_kafka.admin.NewTopic('topic-example-100', 1, 1)
new_topic_avro   = confluent_kafka.admin.NewTopic('topic-example-200', 1, 1)
                  # Number-of-partitions  = 1
                  # Number-of-replicas    = 1
kafka_admin.create_topics([new_topic,]) # CREATE
kafka_admin.create_topics([new_topic_avro,]) # CREATE
pprint.pprint(kafka_admin.list_topics().topics) # LIST

#kafka_admin.delete_topics(['topic-example-1', 'topic-example-2',]) # DELETE