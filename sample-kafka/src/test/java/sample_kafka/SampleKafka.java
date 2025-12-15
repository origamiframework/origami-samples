package sample_kafka;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.origami.common.OrigamiHelper;
import ru.origami.kafka.models.KafkaRecord;
import ru.origami.sample.models.kafka.RequestTopicMessage;
import ru.origami.sample.steps.kafka.KafkaConsumerSteps;
import ru.origami.sample.steps.kafka.KafkaProducerSteps;
import ru.origami.sample.steps.kafka.KafkaSteps;
import ru.origami.sample.utils.DataHelper;
import ru.origami.testit_allure.allure.java_commons.Feature;
import ru.origami.testit_allure.annotations.Description;
import ru.origami.testit_allure.annotations.Link;
import ru.origami.testit_allure.annotations.Story;

import java.util.ArrayList;
import java.util.List;

import static ru.origami.sample.models.kafka.ETopic.REQUEST_TOPIC;

@Feature("Реализация работы с Kafka")
@Story("Работа с топиками Kafka")
@DisplayName("Отправка и чтение сообщений из топика request-topic")
@Link(name = "Документация", url = "https://example.ru/confluence/pages/viewpage.action?pageId=123")
public class SampleKafka {

    KafkaProducerSteps producerSteps = new KafkaProducerSteps();

    KafkaConsumerSteps consumerSteps = new KafkaConsumerSteps();

    KafkaSteps kafkaSteps = new KafkaSteps();

    @Test
    @DisplayName("Отправка сообщения строкой")
    @Description("Пример отправки сообщения в виде строки в 1 партицию")
    void successProducerAsString() {
        RequestTopicMessage request = DataHelper.getRequestTopicMessage();
        String message = OrigamiHelper.getObjectAsJsonString(request);

        producerSteps.setPartition(0).sendMessage(REQUEST_TOPIC, request.getId().toString(), message);
    }

    @Test
    @DisplayName("Отправка сообщения в формате JSON")
    @Description("Пример отправки сообщения в формате JSON")
    void successProducerAsJson() {
        RequestTopicMessage request = DataHelper.getRequestTopicMessage();

        producerSteps.sendMessageAsJson(REQUEST_TOPIC, request.getId().toString(), request);
    }

    @Test
    @DisplayName("Отправка и вычитка всех сообщений из топика")
    @Description("Пример вычитки топика на всю глубину и фильтрация по отправленным id")
    void successConsumer() {
        List<RequestTopicMessage> requestTopicMessages = new ArrayList<>();
        requestTopicMessages.add(DataHelper.getRequestTopicMessage());
        requestTopicMessages.add(DataHelper.getRequestTopicMessage());

        requestTopicMessages.forEach(r -> producerSteps.sendMessageAsJson(REQUEST_TOPIC, r.getId().toString(), r));

        // вычитываем все сообщения из топика
        List<String> searchIds = requestTopicMessages.stream().map(r -> r.getId().toString()).toList();
        List<KafkaRecord<RequestTopicMessage>> readMessages = consumerSteps.readAllFromJson(REQUEST_TOPIC, searchIds, RequestTopicMessage.class);

        // Проверяем вычитанные сообщения
        kafkaSteps.requestsShouldBeEquals(requestTopicMessages, readMessages);
    }

    @Test
    @DisplayName("Подписка на топик")
    @Description("Пример подписки на топик с поиском отправленных сообщений")
    void successConsumerSubscribe() {
        // подписываемся на топик
        consumerSteps.subscribe(REQUEST_TOPIC, RequestTopicMessage.class);

        // отправляем сообщения
        List<RequestTopicMessage> requestTopicMessages = new ArrayList<>();
        requestTopicMessages.add(DataHelper.getRequestTopicMessage());
        requestTopicMessages.add(DataHelper.getRequestTopicMessage());

        requestTopicMessages.forEach(r -> producerSteps.sendMessageAsJson(REQUEST_TOPIC, r.getId().toString(), r));

        // останавливаем подписку на топик и получаем результат
        List<String> searchIds = requestTopicMessages.stream().map(r -> r.getId().toString()).toList();
        List<KafkaRecord<RequestTopicMessage>> readMessages = consumerSteps.unsubscribeWhenGetJsonMessage(REQUEST_TOPIC, searchIds);

        // Проверяем полученные сообщения
        kafkaSteps.requestsShouldBeEquals(requestTopicMessages, readMessages);
    }
}