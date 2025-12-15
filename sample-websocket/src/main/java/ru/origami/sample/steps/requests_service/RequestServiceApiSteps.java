package ru.origami.sample.steps.requests_service;

import io.restassured.response.Response;
import ru.origami.sample.models.RequestModel;
import ru.origami.testit_allure.annotations.Step;

import java.util.UUID;

public class RequestServiceApiSteps extends CommonRequestServiceSteps {

    @Step("Отправляем запрос GET /api/v1/requests")
    public Response getRequests() {
        return getRequestsRequestSpec(null).get();
    }

    @Step("Отправляем запрос GET /api/v1/requests/{id}")
    public Response getRequestById(UUID id) {
        return getRequestsRequestSpec(id, null).get();
    }

    @Step("Отправляем запрос POST /api/v1/requests")
    public Response postRequests(RequestModel request) {
        return getRequestsRequestSpec(request).post();
    }

    @Step("Отправляем запрос PATCH /api/v1/request")
    public Response patchRequests(UUID id, RequestModel request) {
        return getRequestsRequestSpec(id, request).patch();
    }
}