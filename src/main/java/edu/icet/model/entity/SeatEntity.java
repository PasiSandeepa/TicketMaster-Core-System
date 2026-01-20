package edu.icet.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "seats")
@Data
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long eventId;
    private String seatNumber;
    private String status;
    private Long heldByUserId;
    private LocalDateTime holdExpiry;

    @Version
    private Integer version;
}