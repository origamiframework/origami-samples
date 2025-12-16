package ru.origami.sample.models.ibm_mq;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestMessage {

    @JsonProperty(value = "id", required = true)
    private UUID id;

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "note")
    private String note;

    @Override
    public String toString() {
        return name;
    }
}
