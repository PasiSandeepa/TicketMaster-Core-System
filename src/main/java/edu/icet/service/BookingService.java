package edu.icet.service;

import edu.icet.model.dto.BookingRequestDTO;
import edu.icet.model.dto.BookingResponseDTO;

public interface BookingService {

    BookingResponseDTO createBooking(BookingRequestDTO request);
}