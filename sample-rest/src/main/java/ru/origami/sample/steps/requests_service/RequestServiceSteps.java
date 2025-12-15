package ru.origami.sample.steps.requests_service;

import ru.origami.sample.models.RequestModel;
import ru.origami.testit_allure.annotations.Step;

import java.util.List;

import static ru.origami.common.asserts.Asserts.*;

public class RequestServiceSteps extends RequestServiceApiSteps {

    @Step("Проверяем полученные данные")
    public void requestsShouldContains(RequestModel expected, List<RequestModel> respList) {
        assertTrue("requests size", 1 <= respList.size());

        RequestModel actual = respList.stream()
                .filter(r -> r.getId().equals(expected.getId()))
                .findFirst()
                .orElse(null);

        exampleShouldBeEquals(expected, actual);
    }

    @Step("Проверяем объект: {expected.id}")
    public void exampleShouldBeEquals(RequestModel expected, RequestModel actual) {
        assertAll(
                () -> assertEquals("id", expected.getId(), actual.getId()),
                () -> assertEquals("name", expected.getName(), actual.getName()),
                () -> assertEquals("note", expected.getNote(), actual.getNote())
        );
    }
}
