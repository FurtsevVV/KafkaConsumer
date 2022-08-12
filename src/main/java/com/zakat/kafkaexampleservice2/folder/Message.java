package com.zakat.kafkaexampleservice2.folder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@EqualsAndHashCode
class Message {

    private String author;
    private Long number;
    private LocalDateTime date;


}
