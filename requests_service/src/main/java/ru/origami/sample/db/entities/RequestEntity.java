package ru.origami.sample.db.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "requests")
@Data
public class RequestEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String note;
}
