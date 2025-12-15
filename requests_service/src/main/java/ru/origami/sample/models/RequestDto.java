package ru.origami.sample.models;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class RequestDto {

    private UUID id;
    private String name;
    private String note;
}
