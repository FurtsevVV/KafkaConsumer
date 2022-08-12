package com.zakat.kafkaexampleservice2.folder;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

@Service
public class KafKaConsumerService {

    public static final String TOPIC_NAME = "topic1";
    public static final String GROUP_ID = "group_id";
    private final String MESSAGE_GROUP_ID = "message_group_id";
    public static final String KEY = "KEY1";
    public static final String KEY2 = "KEY2";




    private final Logger logger =
            LoggerFactory.getLogger(KafKaConsumerService.class);


    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC_NAME, partitions = "1")})
    public void consume(String message)
    {
        logger.info(String.format("Message recieved -> %s", message));
    }


    @KafkaListener(groupId = MESSAGE_GROUP_ID, containerFactory = "messageKafkaListenerContainerFactory",
    topicPartitions = {@TopicPartition(topic = TOPIC_NAME, partitions = {"2"})})
    public void consumeMessage(ConsumerRecord<String, Message> message)
    {

        logger.info("Key: " + message.key());
        logger.info("Value = " + message.value());
        Message newMessage  = message.value();
        logger.info("NewMessage = " + newMessage);

    }


}
