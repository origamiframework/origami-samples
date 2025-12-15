package ru.origami.sample.steps.requests_service;

import ru.origami.common.environment.Environment;
import ru.origami.rest.models.RequestSpec;
import ru.origami.sample.models.RequestModel;
import ru.origami.sample.steps.CommonSteps;

import java.util.UUID;

public class CommonRequestServiceSteps extends CommonSteps {

    protected RequestSpec getRequestsRequestSpec(RequestModel request) {
        return getRequestsServiceSpecBuilder()
                .setBasePath(Environment.get("request.service.requests.relative.uri"))
                .setContentType("application/json")
                .setNotNullBody(request)
                .build();
    }

    protected RequestSpec getRequestsRequestSpec(UUID id, RequestModel request) {
        return getRequestsServiceSpecBuilder()
                .setBasePath(Environment.get("request.service.requests.by.id.relative.uri"))
                .setContentType("application/json")
                .addPathParam("id", id)
                .setNotNullBody(request)
                .build();
    }
}