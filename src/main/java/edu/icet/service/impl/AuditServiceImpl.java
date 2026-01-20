package edu.icet.service.impl;

import edu.icet.model.dto.AuditLogDTO;
import edu.icet.model.entity.AuditLogEntity;
import edu.icet.repository.AuditLogRepository;
import edu.icet.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveAuditLog(AuditLogDTO auditLogDto) {
        AuditLogEntity entity = modelMapper.map(auditLogDto, AuditLogEntity.class);
        auditLogRepository.save(entity);
    }

    @Override
    public List<AuditLogDTO> getAllLogs() {
        return auditLogRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, AuditLogDTO.class))
                .collect(Collectors.toList());
    }


        @Override
        public void add(AuditLogDTO auditLogDto) {

            AuditLogEntity logEntity = modelMapper.map(auditLogDto, AuditLogEntity.class);


            if (logEntity.getTimestamp() == null) {
                logEntity.setTimestamp(LocalDateTime.now());
            }

            auditLogRepository.save(logEntity);

            System.out.println("✅ Audit Log saved: " + logEntity.getAction());
        }
    }