package ru.origami.sample.utils;

import ru.origami.test_containers.TestContainerName;
import ru.origami.test_containers.TestContainers;

import java.util.List;

@TestContainerName("kafka")
public class KafkaContainer extends TestContainers {

    public KafkaContainer() {
        withFixedPorts();
        withKafka();
        kafkaTopics = List.of(
                getTopic("request-topic", 1, (short) 1, true),
                getTopic("response-topic", 1, (short) 1, false)
        );
    }
}
