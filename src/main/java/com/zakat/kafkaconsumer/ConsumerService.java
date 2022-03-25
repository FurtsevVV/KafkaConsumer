package com.zakat.kafkaconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {


@KafkaListener(topics = "messages", groupId = "message_group_id", containerFactory = "kafkaListenerContainerFactory")
    public void consume(Message message){
    System.out.println("Get message: " + message);
}

}
