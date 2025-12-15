package ru.origami.sample.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.origami.sample.db.entities.RequestEntity;
import ru.origami.sample.db.repositories.RequestRepository;
import ru.origami.sample.models.RequestDto;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/requests")
@Tag(name = "Requests", description = "Методы для работы с Request сущностью")
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Operation(summary = "Создать новый Request")
    @PostMapping
    public ResponseEntity<RequestDto> create(@RequestBody RequestDto requestDto) {
        RequestEntity entity = new RequestEntity()
                .setName(requestDto.getName())
                .setNote(requestDto.getNote());

        RequestEntity saved = requestRepository.save(entity);

        RequestDto response = new RequestDto()
                .setId(saved.getId())
                .setName(saved.getName())
                .setNote(saved.getNote());

        return ResponseEntity
                .created(URI.create("/api/v1/requests/%s".formatted(saved.getId())))
                .body(response);
    }

    @Operation(summary = "Получить все Requests")
    @GetMapping
    public List<RequestDto> getAll() {
        return requestRepository.findAll()
                .stream()
                .map(r -> new RequestDto()
                        .setId(r.getId())
                        .setName(r.getName())
                        .setNote(r.getNote()))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Получить Request по id")
    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getById(@PathVariable("id") UUID id) {
        return requestRepository.findById(id)
                .map(r -> new RequestDto()
                        .setId(r.getId())
                        .setName(r.getName())
                        .setNote(r.getNote()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Обновление Request")
    @PatchMapping("/{id}")
    public ResponseEntity<RequestDto> patch(@PathVariable("id") UUID id, @RequestBody RequestDto patch) {
        return requestRepository.findById(id)
                .map(entity -> {
                    if (Objects.nonNull(patch.getName())) {
                        entity.setName(patch.getName());
                    }

                    if (Objects.nonNull(patch.getNote())) {
                        entity.setNote(patch.getNote());
                    }

                    RequestEntity saved = requestRepository.save(entity);

                    RequestDto response = new RequestDto()
                            .setId(saved.getId())
                            .setName(saved.getName())
                            .setNote(saved.getNote());

                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
