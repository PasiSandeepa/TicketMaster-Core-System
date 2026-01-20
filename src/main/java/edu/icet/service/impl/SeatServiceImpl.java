package edu.icet.service.impl;

import edu.icet.exeption.SeatLockedException;
import edu.icet.model.entity.SeatEntity;
import edu.icet.repository.SeatRepository;
import edu.icet.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    @Override
    public String holdSeat(Long seatId, Long userId) {

        SeatEntity seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        LocalDateTime now = LocalDateTime.now();

        if ("AVAILABLE".equals(seat.getStatus())) {
            return updateSeatToHeld(seat, userId, now);
        }

        if ("HELD".equals(seat.getStatus())) {
            if (now.isAfter(seat.getHoldExpiry())) {
                return updateSeatToHeld(seat, userId, now);
            } else {
                long remainingSeconds = Duration.between(now, seat.getHoldExpiry()).getSeconds();
                throw new SeatLockedException(remainingSeconds);
            }
        }

        return "Seat is already SOLD or unavailable.";
    }

    private String updateSeatToHeld(SeatEntity seat, Long userId, LocalDateTime now) {
        seat.setStatus("HELD");
        seat.setHeldByUserId(userId);
        seat.setHoldExpiry(now.plusMinutes(10));
        seatRepository.save(seat);
        return "Seat held successfully for 10 minutes.";
    }

}
