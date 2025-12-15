package ru.origami.sample.models.ws;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import ru.origami.websocket.models.WsTopic;

@Getter
@AllArgsConstructor
@ToString
public enum EWsTopic implements WsTopic {

    NOTIFY_TOPIC("/topic/notify");

    private final String topic;
}
