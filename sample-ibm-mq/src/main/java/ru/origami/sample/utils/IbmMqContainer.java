package ru.origami.sample.utils;

import ru.origami.test_containers.TestContainerName;
import ru.origami.test_containers.TestContainers;

import java.util.List;

@TestContainerName("ibm_mq")
public class IbmMqContainer extends TestContainers {

    public IbmMqContainer() {
        withFixedPorts();
        withIbmMq();
        ibmMqQueues = List.of("REQUEST.QUEUE", "RESPONSE.QUEUE");
    }
}
