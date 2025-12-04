package ru.origami.sample.steps.kafka;

import ru.origami.kafka.models.KafkaRecord;
import ru.origami.sample.models.kafka.RequestTopicMessage;
import ru.origami.testit_allure.annotations.Step;

import java.util.Comparator;
import java.util.List;

import static ru.origami.common.asserts.Asserts.*;

public class KafkaSteps {

    @Step("Проверяем полученные данные")
    public void requestsShouldBeEquals(List<RequestTopicMessage> expectedList, List<KafkaRecord<RequestTopicMessage>> actualList) {
        assertListSize("requests", expectedList.size(), actualList);

        expectedList.sort(Comparator.comparing(RequestTopicMessage::getId));
        actualList.sort(Comparator.comparing(r -> r.getValue().getId()));

        for (int i = 0; i < expectedList.size(); i++) {
            requestShouldBeEquals(expectedList.get(i), actualList.get(i));
        }
    }

    @Step("Проверяем запрос: {expected.id}")
    public void requestShouldBeEquals(RequestTopicMessage expected, KafkaRecord<RequestTopicMessage> actual) {
        assertAll(
                () -> assertEquals("key", expected.getId().toString(), actual.getKey()),
                () -> assertEquals("id", expected.getId(), actual.getValue().getId()),
                () -> assertEquals("name", expected.getName(), actual.getValue().getName()),
                () -> assertEquals("note", expected.getNote(), actual.getValue().getNote())
        );
    }
}
