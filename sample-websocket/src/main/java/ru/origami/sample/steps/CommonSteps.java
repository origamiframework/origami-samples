package ru.origami.sample.steps;

import io.restassured.RestAssured;
import ru.origami.common.environment.Environment;
import ru.origami.rest.RestSteps;
import ru.origami.rest.models.RequestSpecBuilder;

public class CommonSteps extends RestSteps {

    public CommonSteps() {
        RestAssured.useRelaxedHTTPSValidation();
    }

    protected RequestSpecBuilder getRequestsServiceSpecBuilder() {
        return getRequestSpecBuilder()
                .setBaseUri(Environment.get("requests.service.url"))
                .setNotNullPort(Environment.getIntWithNullValue("requests.service.port"))
                .addHeader("Accept", "application/json");
    }
}