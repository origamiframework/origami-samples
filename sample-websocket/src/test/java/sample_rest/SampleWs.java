package sample_rest;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.origami.common.OrigamiHelper;
import ru.origami.sample.models.RequestModel;
import ru.origami.sample.models.ws.NotifyWsMessage;
import ru.origami.sample.steps.requests_service.RequestServiceSteps;
import ru.origami.sample.steps.ws.RequestsWsSteps;
import ru.origami.sample.utils.DataHelper;
import ru.origami.testit_allure.allure.java_commons.Feature;
import ru.origami.testit_allure.annotations.Description;
import ru.origami.testit_allure.annotations.Link;
import ru.origami.testit_allure.annotations.Story;

import java.util.List;

import static ru.origami.sample.models.ws.EWsTopic.NOTIFY_TOPIC;

@Feature("Реализация работы с WebSocket")
@Story("Работа с WebSocket")
@DisplayName("Чтение сообщений, отправленных по Ws")
@Link(name = "Документация", url = "https://example.ru/confluence/pages/viewpage.action?pageId=123")
public class SampleWs {

    RequestServiceSteps requestServiceSteps = new RequestServiceSteps();

    RequestsWsSteps wsSteps = new RequestsWsSteps();

    @Test
    @DisplayName("Получение сообщения по WebSocket при создании заявки")
    @Description("Пример получения сообщения по WebSocket")
    void successPostRequest() {
        wsSteps.subscribe(NOTIFY_TOPIC, NotifyWsMessage.class);

        Response resp = requestServiceSteps.postRequests(DataHelper.getRequest());
        RequestModel request = RequestModel.createResponse(resp);
        requestServiceSteps.requestsShouldContains(request, List.of(request));

        List<NotifyWsMessage> wsMessages = wsSteps.unsubscribeAndGetResults(NOTIFY_TOPIC);
        wsSteps.newRequestShouldBeEquals(request, wsMessages);
    }

    @Test
    @DisplayName("Получение сообщения по WebSocket при изменении заявки")
    @Description("Пример получения сообщения по WebSocket")
    void successPatchRequest() {
        Response createResp = requestServiceSteps.postRequests(DataHelper.getRequest());
        RequestModel request = RequestModel.createResponse(createResp);

        wsSteps.subscribe(NOTIFY_TOPIC, NotifyWsMessage.class);

        Response resp = requestServiceSteps.patchRequests(request.getId(), request.setName(OrigamiHelper.getRandomLatinString(10)));
        RequestModel actualRequest = RequestModel.createResponse(resp);
        requestServiceSteps.requestsShouldContains(request, List.of(actualRequest));

        List<NotifyWsMessage> wsMessages = wsSteps.unsubscribeAndGetResults(NOTIFY_TOPIC);
        wsSteps.changedRequestShouldBeEquals(request, wsMessages);
    }
}