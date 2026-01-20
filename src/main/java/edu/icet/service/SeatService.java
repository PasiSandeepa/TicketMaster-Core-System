package edu.icet.service;

import edu.icet.model.dto.SeatRequestDTO;
import edu.icet.model.dto.SeatResponseDTO;

public interface SeatService {

    SeatResponseDTO holdSeat(Long seatId, Long userId);


    void saveSeat(SeatRequestDTO seatDto);
}