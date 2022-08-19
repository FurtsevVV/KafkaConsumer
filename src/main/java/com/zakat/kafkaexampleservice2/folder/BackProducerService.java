package com.zakat.kafkaexampleservice2.folder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;


@Service
@Slf4j
@RequiredArgsConstructor
public class BackProducerService {

    private final String KEY3 = "KEY3";
    private final KafkaTemplate<String, BackMessage> backMessageKafkaTemplate;

    public void sendObject(String topic, Message message) {

        BackMessage backMessage = new BackMessage();
        backMessage.setMesssage(message);
        backMessage.setName("Vel10");
        backMessage.setQty(7225L);
        log.info("sending object BackMessage='{}' to topic='{}'", backMessage, topic);
        ListenableFuture<SendResult<String, BackMessage>> future1 = backMessageKafkaTemplate.send(topic, 0, KEY3, backMessage);
        future1.addCallback(System.out::println, System.err::println);
    }


}
