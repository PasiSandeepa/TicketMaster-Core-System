package edu.icet.controller;

import edu.icet.model.dto.BookingRequestDTO;
import edu.icet.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@CrossOrigin
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequestDTO request) {
        try {

            String result = bookingService.createBooking(request);


            return ResponseEntity.ok(result);
        } catch (Exception e) {

            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}