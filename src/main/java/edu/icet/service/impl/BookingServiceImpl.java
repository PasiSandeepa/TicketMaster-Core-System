package edu.icet.service.impl;

import edu.icet.annotation.AuditFailure;
import edu.icet.model.entity.*;
import edu.icet.repository.*;
import edu.icet.service.BookingService;
import edu.icet.service.PriceCalculatorService;
import edu.icet.service.SeatService;
import edu.icet.model.dto.BookingRequestDTO;
import edu.icet.model.dto.BookingResponseDTO; // Response DTO එකක් භාවිතා කිරීම නිර්දේශිතයි
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final SeatService seatService;
    private final PriceCalculatorService priceCalculator;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final SeatRepository seatRepository;

    @Override
    @Transactional
    @AuditFailure
    public String createBooking(BookingRequestDTO request) {


        seatService.holdSeat(request.getSeatId(), request.getUserId());


        UserEntity user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        SeatEntity seat = seatRepository.findById(request.getSeatId())
                .orElseThrow(() -> new RuntimeException("Seat not found"));


        EventEntity event = seat.getEvent();


        Double finalPrice = priceCalculator.calculatePrice(user, event);
        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setSeat(seat);
        booking.setAmountPaid(finalPrice);
        booking.setStatus("CONFIRMED");
        booking.setTimestamp(LocalDateTime.now());

        bookingRepository.save(booking);


        seat.setStatus("SOLD");
        seatRepository.save(seat);


        String responseMessage = "Booking confirmed! Amount paid: " + finalPrice;
        if ("PLATINUM".equalsIgnoreCase(user.getTier())) {
            responseMessage += " [Priority Access: True]";
        }

        return responseMessage;
    }
}