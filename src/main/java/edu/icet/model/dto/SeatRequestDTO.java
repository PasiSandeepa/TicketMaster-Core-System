package edu.icet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SeatRequestDTO {
    private String seatNumber;

    @JsonProperty("eventId")
    private Long eventId;
}