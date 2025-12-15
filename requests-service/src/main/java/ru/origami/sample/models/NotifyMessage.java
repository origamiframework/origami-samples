package ru.origami.sample.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NotifyMessage {

    private String header;
    private String message;
}
