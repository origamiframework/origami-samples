package ru.origami.sample.models.ibm_mq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.origami.ibm_mq.models.Queue;

@Getter
@AllArgsConstructor
@ToString
public enum EQueue implements Queue {

    REQUEST_QUEUE("REQUEST.QUEUE"),
    RESPONSE_QUEUE("RESPONSE.QUEUE");

    private final String queue;
}
