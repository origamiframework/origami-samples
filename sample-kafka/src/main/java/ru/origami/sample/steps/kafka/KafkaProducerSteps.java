package ru.origami.sample.steps.kafka;

import ru.origami.common.environment.Environment;
import ru.origami.kafka.ProducerSteps;
import ru.origami.kafka.models.ESecurityProtocol;
import ru.origami.kafka.models.Properties;

public class KafkaProducerSteps extends ProducerSteps {

    public KafkaProducerSteps() {
        properties = new Properties.Builder()
                .setBootstrapServers(Environment.get("kafka.bootstrap-servers"))
                .addSecurityProtocol(ESecurityProtocol.PLAINTEXT, "test_containers")
                .addSecurityProtocol(ESecurityProtocol.PLAINTEXT, "local.*")
                .setUsername(Environment.getWithNullValue("kafka.username"))
                .setPassword(Environment.getWithNullValue("kafka.password"))
                .setSslTruststoreLocation(Environment.getWithNullValue("kafka.ssl.truststore.location"))
                .setSslTruststorePassword(Environment.getWithNullValue("kafka.ssl.truststore.password"))
                .setTopicPrefix(Environment.getWithNullValue("kafka.topic.prefix"))
                .build();
    }
}
