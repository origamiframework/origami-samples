package ru.origami.sample.db.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.origami.sample.db.entities.RequestEntity;

import java.util.UUID;

public interface RequestRepository extends JpaRepository<RequestEntity, UUID> {
}
