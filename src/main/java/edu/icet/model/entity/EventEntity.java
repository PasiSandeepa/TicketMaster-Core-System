package edu.icet.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double basePrice;
    private Boolean isHighDemand;
    private LocalDateTime eventDate;


    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<SeatEntity> seats;
}