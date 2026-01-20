package edu.icet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditLogDTO {
    private Long id;
    private String action;
    private String status;
    private String errorMessage;
    private Long userId;
    private LocalDateTime timestamp;
}