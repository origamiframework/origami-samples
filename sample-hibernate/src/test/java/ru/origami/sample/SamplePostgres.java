package ru.origami.sample;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.entities.Request;
import ru.origami.sample.steps.MyServiceFixtureSteps;
import ru.origami.testit_allure.allure.java_commons.Feature;
import ru.origami.testit_allure.annotations.Description;
import ru.origami.testit_allure.annotations.Link;
import ru.origami.testit_allure.annotations.Story;

import java.util.List;
import java.util.UUID;

@Feature("Реализация работы с базой данных")
@Story("Работа с базой данных Postgres")
@DisplayName("Выполнение запросов к базе данных")
@Link(name = "Документация", url = "https://example.ru/confluence/pages/viewpage.action?pageId=123")
public class SamplePostgres {

    MyServiceFixtureSteps fixtureSteps = new MyServiceFixtureSteps();

    UUID id;

    @BeforeAll
    @DisplayName("Добавление заявки в бд")
    void createRequest() {
        id = OrigamiHelper.getUUID();
        fixtureSteps.addRequest(id);
    }

    @Test
    @DisplayName("Выбор всех заявок из бд")
    @Description("Пример выборки из базы данных")
    void successSelect() {
        List<Request> requests = fixtureSteps.getAllRequests();
    }

    @Test
    @DisplayName("Выбор заявки по id")
    @Description("Пример выборки конкретной заявки из базы данных")
    void successSelectById() {
        Request request = fixtureSteps.getRequestById(id);
    }

    @Test
    @DisplayName("Добавление заявки в базу данных")
    @Description("Пример добавления записи в базу данных")
    void successInsert() {
        UUID id = OrigamiHelper.getUUID();
        fixtureSteps.addRequest(id);
    }

    @Test
    @DisplayName("Обновление заявки в базе данных")
    @Description("Пример обновления записи в базе данных")
    void successUpdate() {
        fixtureSteps.updateCommentById(id, OrigamiHelper.getRandomCyrillicString(5));
    }
}