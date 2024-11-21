package edu.miu.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Data
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditId;

    @Column(nullable = false)
    private String entityType;

    @Column(nullable = false)
    private Long entityId;

    @Column(nullable = false)
    private String changeType;

    private String changedBy;

    private LocalDateTime changeTimestamp;

    @Lob
    private String previousState;

    @Lob
    private String newState;

}
