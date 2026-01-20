package edu.icet.controller;

import edu.icet.model.entity.EventEntity;
import edu.icet.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {
    private final EventRepository eventRepository;

    @PostMapping
    public ResponseEntity<EventEntity> createEvent(@RequestBody EventEntity event) {
        return ResponseEntity.ok(eventRepository.save(event));
    }
}