package com.zakat.kafkaexampleservice2.folder;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.List;


@Service
public class KafKaConsumerService {


    @Autowired
    private static OtherSideService otherSideService;
    private static BackProducerService backProducerService;
    private static KafkaConsumerConfig kafkaConsumerConfig;


    public static final String TOPIC_NAME = "topic1";
    public static final String GROUP_ID = "group_id";
    private final String MESSAGE_GROUP_ID = "message_group_id";
    public static final String KEY = "KEY1";
    public static final String KEY2 = "KEY2";




    private final Logger logger =
            LoggerFactory.getLogger(KafKaConsumerService.class);


    @KafkaListener(groupId = GROUP_ID, topicPartitions = {@TopicPartition(topic = TOPIC_NAME, partitions = "1")})
    public void consume(@Payload String record,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String ket){
        logger.info(String.format("Message recieved -> %s", record));
        logger.info(String.format("RECEIVED_MESSAGE_KEY  -> %s", ket));
//        logger.info(String.format("ACKNOWLEDGMENT headers -> %s", ack));
//        logger.info(String.format("OFFSET headers -> %s", offset));
//        Message message1 = otherSideService.returnHandledMessage(message);

    }


    //метод принимает Лист строк
    @KafkaListener(id = "list", topics = TOPIC_NAME, containerFactory = "batchFactory")
    public void listen(List<String> list) {
    logger.info("Lisi String = " + list);
    }


    @KafkaListener(groupId = MESSAGE_GROUP_ID, containerFactory = "messageKafkaListenerContainerFactory",
    topicPartitions = {@TopicPartition(topic = TOPIC_NAME, partitions = {"2"})})
    public void consumeMessage(ConsumerRecord<String, Message> message)
    {

        logger.info("Key: " + message.key());
        logger.info("Value = " + message.value());
        Message newMessage  = message.value();
        logger.info("NewMessage = " + newMessage);

//        backProducerService.sendObject(TOPIC_NAME, newMessage);

    }


    public String returnSomething() {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(kafkaConsumerConfig.stringProperties());
        consumer.subscribe(Collections.singleton(TOPIC_NAME));
        while (true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(50));
            for (ConsumerRecord<String, String> record: records) {
                logger.info("Poll message ConsumerRecord: " + record);
                logger.info("Get offset from record: " + record.offset() + " get KEY/value" + record.key() + " " +
                        record.value());
            }
        }

    }



}
