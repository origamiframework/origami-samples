package ru.origami.sample.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

import static ru.origami.hibernate.CommonFixtureSteps.DYNAMIC_SCHEMA;

@Entity
@Getter
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "requests", schema = DYNAMIC_SCHEMA)
public class Request implements Serializable {

    @ToString.Include
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "comment")
    private String comment;
}
