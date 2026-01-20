package edu.icet.service;

import edu.icet.model.dto.BookingRequestDTO;

public interface BookingService {
    String createBooking(BookingRequestDTO request);
}
