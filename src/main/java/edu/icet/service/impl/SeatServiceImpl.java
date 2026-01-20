package edu.icet.service.impl;

import edu.icet.exeption.SeatLockedException;
import edu.icet.model.dto.SeatRequestDTO;
import edu.icet.model.dto.SeatResponseDTO;
import edu.icet.model.entity.EventEntity;
import edu.icet.model.entity.SeatEntity;
import edu.icet.repository.EventRepository;
import edu.icet.repository.SeatRepository;
import edu.icet.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;

    @Transactional
    @Override
    public SeatResponseDTO holdSeat(Long seatId, Long userId) {


        SeatEntity seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();


        if ("AVAILABLE".equals(seat.getStatus()) ||
                ("HELD".equals(seat.getStatus()) && now.isAfter(seat.getHoldExpiry()))) {

            return updateSeatToHeld(seat, userId, now);
        }


        if ("HELD".equals(seat.getStatus())) {
            long remainingSeconds = Duration.between(now, seat.getHoldExpiry()).getSeconds();
            throw new SeatLockedException(remainingSeconds);
        }

        throw new RuntimeException("Seat is already SOLD or unavailable.");
    }

    private SeatResponseDTO updateSeatToHeld(SeatEntity seat, Long userId, LocalDateTime now) {
        seat.setStatus("HELD");
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(10));

        SeatEntity savedSeat = seatRepository.save(seat);


        SeatResponseDTO response = modelMapper.map(savedSeat, SeatResponseDTO.class);
        response.setMessage("Seat held successfully for 10 minutes.");
        response.setRemainingSeconds(600L);

        return response;
    }

    @Override
    public void saveSeat(SeatRequestDTO seatDto) {

        SeatEntity seatEntity = modelMapper.map(seatDto, SeatEntity.class);


        EventEntity event = eventRepository.findById(seatDto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found with ID: " + seatDto.getEventId()));

        seatEntity.setEvent(event);
        seatEntity.setStatus("AVAILABLE");

        seatRepository.save(seatEntity);
    }
}