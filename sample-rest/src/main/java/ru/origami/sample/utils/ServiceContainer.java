package ru.origami.sample.utils;

import ru.origami.test_containers.TestContainer;
import ru.origami.test_containers.TestContainerName;
import ru.origami.test_containers.TestContainers;

@TestContainerName("requests-service")
public class ServiceContainer extends TestContainers {

    public ServiceContainer() {
        withFixedPorts();
        TestContainer requestsServiceContainer = buildDefaultAppContainer("requests-service", "latest",
                "requests_service_container");
        containers.add(requestsServiceContainer);
    }
}
