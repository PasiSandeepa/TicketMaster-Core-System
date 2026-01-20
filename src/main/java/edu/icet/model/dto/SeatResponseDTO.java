package edu.icet.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SeatResponseDTO {
    private Long seatId;
    private String seatNumber;
    private String status;
    private Long remainingSeconds;
    private String message;
}