package ru.origami.sample.models.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.origami.kafka.models.Topic;

@Getter
@AllArgsConstructor
@ToString
public enum ETopic implements Topic {

    REQUEST_TOPIC("request-topic"),
    RESPONSE_TOPIC("response-topic");

    private final String topic;
}
