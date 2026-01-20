package edu.icet.model.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookingResponseDTO {
    private Long bookingId;
    private Double finalPrice;
    private String status;
    private boolean priorityAccess;
}