package edu.icet.service;

import edu.icet.model.dto.AuditLogDTO;
import java.util.List;

public interface AuditService {
    void saveAuditLog(AuditLogDTO auditLogDto);
    List<AuditLogDTO> getAllLogs();
    void add(AuditLogDTO auditLogDto);
}