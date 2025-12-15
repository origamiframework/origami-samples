package sample_rest;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.RequestModel;
import ru.origami.sample.steps.requests_service.RequestServiceSteps;
import ru.origami.sample.utils.DataHelper;
import ru.origami.testit_allure.allure.java_commons.Feature;
import ru.origami.testit_allure.annotations.Description;
import ru.origami.testit_allure.annotations.Link;
import ru.origami.testit_allure.annotations.Story;

import java.util.List;

@Feature("Реализация работы с HTTP запросами")
@Story("Работа с HTTP запросами")
@DisplayName("Отправка HTTP запросов")
@Link(name = "Документация", url = "https://example.ru/confluence/pages/viewpage.action?pageId=123")
public class SampleRest {

    RequestServiceSteps requestServiceSteps = new RequestServiceSteps();

    RequestModel request;

    @BeforeAll
    @DisplayName("Создание запроса")
    public void createRequest() {
        Response resp = requestServiceSteps.postRequests(DataHelper.getRequest());

        request = RequestModel.createResponse(resp);
    }

    @Test
    @DisplayName("Получение всех заявок")
    @Description("Пример вызова GET метода")
    void successGetAllRequests() {
        Response resp = requestServiceSteps.getRequests();
        List<RequestModel> requests = RequestModel.createResponseList(resp);
        requestServiceSteps.requestsShouldContains(request, requests);
    }

    @Test
    @DisplayName("Получение заявки по id")
    @Description("Пример вызова GET метода")
    void successGetRequestById() {
        Response resp = requestServiceSteps.getRequestById(request.getId());
        RequestModel request = RequestModel.createResponse(resp);
        requestServiceSteps.requestsShouldContains(request, List.of(request));
    }

    @Test
    @DisplayName("Создание заявки")
    @Description("Пример вызова POST метода")
    void successPostRequest() {
        Response resp = requestServiceSteps.postRequests(DataHelper.getRequest());
        request = RequestModel.createResponse(resp);
        requestServiceSteps.requestsShouldContains(request, List.of(request));
    }

    @Test
    @DisplayName("Изменение заявки")
    @Description("Пример вызова PATCH метода")
    void successPatchRequest() {
        Response resp = requestServiceSteps.patchRequests(request.getId(), request.setNote(OrigamiHelper.getRandomLatinString(10)));
        RequestModel actualRequest = RequestModel.createResponse(resp);
        requestServiceSteps.requestsShouldContains(request, List.of(actualRequest));
    }
}