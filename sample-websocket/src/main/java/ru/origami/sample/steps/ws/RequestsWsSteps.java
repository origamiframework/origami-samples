package ru.origami.sample.steps.ws;

import ru.origami.common.environment.Environment;
import ru.origami.sample.models.RequestModel;
import ru.origami.sample.models.ws.NotifyWsMessage;
import ru.origami.testit_allure.annotations.Step;
import ru.origami.websocket.WsProperties;
import ru.origami.websocket.WsSteps;

import java.util.List;

import static ru.origami.common.asserts.Asserts.*;

public class RequestsWsSteps extends WsSteps {

    public RequestsWsSteps() {
        properties = WsProperties.Builder()
                .setUrl(Environment.get("websocket.url"))
                .setPort(Environment.getWithNullValue("websocket.port"))
                .setEndpoint(Environment.get("websocket.endpoint"))
                .setWithWss(Boolean.parseBoolean(Environment.getWithNullValue("websocket.with.wss")))
                .build();
    }

    @Step("Проверяем нового клиента по Ws")
    public void newRequestShouldBeEquals(RequestModel expected, List<NotifyWsMessage> wsMessages) {
        assertAll(
                () -> assertListSize("messages", 1, wsMessages),
                () -> assertEquals("header", "Новый запрос", wsMessages.get(0).getHeader()),
                () -> assertEquals("header", expected.getName(), wsMessages.get(0).getMessage())
        );
    }

    @Step("Проверяем изменение клиента по Ws")
    public void changedRequestShouldBeEquals(RequestModel expected, List<NotifyWsMessage> wsMessages) {
        assertAll(
                () -> assertListSize("messages", 1, wsMessages),
                () -> assertEquals("header", "Обновлен запрос", wsMessages.get(0).getHeader()),
                () -> assertEquals("header", expected.getName(), wsMessages.get(0).getMessage())
        );
    }
}
