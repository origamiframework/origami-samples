package ru.origami.sample.models.ws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@NoArgsConstructor
@ToString
public class NotifyWsMessage {

    @JsonProperty(value = "header", required = true)
    private String header;

    @JsonProperty(value = "message", required = true)
    private String message;
}
