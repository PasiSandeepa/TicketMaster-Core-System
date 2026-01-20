package edu.icet.controller;

import edu.icet.model.dto.SeatRequestDTO;
import edu.icet.model.dto.SeatResponseDTO;
import edu.icet.model.entity.EventEntity;
import edu.icet.model.entity.SeatEntity;
import edu.icet.repository.EventRepository;
import edu.icet.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<SeatEntity> createSeat(@RequestBody SeatRequestDTO seatDto) {

        EventEntity event = eventRepository.findById(seatDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + seatDto.getEventId()));


        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setSeatNumber(seatDto.getSeatNumber());
        seatEntity.setStatus("AVAILABLE");
        seatEntity.setEvent(event);

        SeatEntity savedSeat = seatRepository.save(seatEntity);
        return ResponseEntity.ok(savedSeat);
    }
}