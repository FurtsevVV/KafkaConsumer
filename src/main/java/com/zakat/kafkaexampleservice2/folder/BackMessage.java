package com.zakat.kafkaexampleservice2.folder;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class BackMessage {

    private String name;
    private Long qty;
    private Message messsage;
}
