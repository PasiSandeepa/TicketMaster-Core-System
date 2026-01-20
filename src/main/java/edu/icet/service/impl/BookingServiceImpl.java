package edu.icet.service.impl;

import edu.icet.annotation.AuditFailure;
import edu.icet.model.dto.BookingResponseDTO;
import edu.icet.model.dto.BookingRequestDTO;
import edu.icet.model.entity.BookingEntity;
import edu.icet.model.entity.EventEntity;
import edu.icet.model.entity.SeatEntity;
import edu.icet.model.entity.UserEntity;
import edu.icet.repository.*;
import edu.icet.service.BookingService;
import edu.icet.service.PriceCalculatorService;
import edu.icet.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private final SeatRepository seatRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    @AuditFailure
    public BookingResponseDTO createBooking(BookingRequestDTO request) {


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

        BookingEntity savedBooking = bookingRepository.save(booking);


        seat.setStatus("SOLD");
        seatRepository.save(seat);


        BookingResponseDTO response = modelMapper.map(savedBooking, BookingResponseDTO.class);


        response.setBookingId(savedBooking.getId());
        response.setFinalPrice(finalPrice);

        response.setPriorityAccess("PLATINUM".equalsIgnoreCase(user.getTier()));

        return response;
    }
}