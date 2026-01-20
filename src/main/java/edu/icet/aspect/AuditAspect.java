package edu.icet.aspect;

import edu.icet.model.entity.AuditLogEntity;
import edu.icet.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditLogRepository auditLogRepository;


    @AfterThrowing(pointcut = "@annotation(edu.icet.annotation.AuditFailure)", throwing = "ex")
    public void logBookingFailure(JoinPoint joinPoint, Exception ex) {


        Object[] args = joinPoint.getArgs();
        String userId = (args.length > 0) ? args[0].toString() : "UNKNOWN";

        AuditLogEntity log = new AuditLogEntity();
        log.setAction("BOOKING_FAILURE");
        log.setUserId(Long.valueOf(userId));
        log.setDetails(ex.getMessage());
        log.setTimestamp(LocalDateTime.now());

        auditLogRepository.save(log);
        System.out.println("Audit Log Saved: Booking failed for user " + userId);
    }
}