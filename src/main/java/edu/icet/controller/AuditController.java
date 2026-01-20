package edu.icet.controller;

import edu.icet.model.dto.AuditLogDTO;
import edu.icet.model.entity.AuditLogEntity;
import edu.icet.repository.AuditLogRepository;
import edu.icet.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
public class AuditController {


    private final AuditService auditLogService;
    private final AuditLogRepository auditLogRepository;

    @GetMapping
    public ResponseEntity<List<AuditLogEntity>> getAllLogs() {
        return ResponseEntity.ok(auditLogRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<String> addAuditLog(@RequestBody AuditLogDTO auditLogDto) {

        auditLogService.add(auditLogDto);
        return ResponseEntity.ok("✅ Audit Log added successfully!");
    }
}