package ru.origami.sample.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestModel {

    @JsonProperty(value = "id", required = true)
    private UUID id;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "note")
    private String note;

    public static RequestModel createResponse(Response response) {
        return response.getBody().as(RequestModel.class);
    }

    public static List<RequestModel> createResponseList(Response response) {
        return response.getBody().jsonPath().getList("", RequestModel.class);
    }

    @Override
    public String toString() {
        return name;
    }
}