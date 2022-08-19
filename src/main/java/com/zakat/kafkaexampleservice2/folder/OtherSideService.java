package com.zakat.kafkaexampleservice2.folder;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class OtherSideService {

    public Message returnHandledMessage(String stroka){
        Message message = new Message();
        message.setAuthor("Author is " + stroka);
        message.setDate(LocalDateTime.now().minusSeconds(10));
        message.setNumber((long) stroka.length());
        return message;
    }

}
