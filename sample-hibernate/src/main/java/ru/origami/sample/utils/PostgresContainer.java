package ru.origami.sample.utils;

import ru.origami.test_containers.TestContainerName;
import ru.origami.test_containers.TestContainers;

@TestContainerName("postgres")
public class PostgresContainer extends TestContainers {

    public PostgresContainer() {
        withFixedPorts();
        withPostgres();
    }
}
