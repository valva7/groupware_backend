package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_line")
public class ApprovalLineEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 승인/참조 구분
    @Column(nullable = false, length = 20)
    private String type;

    // 결재 요청 (FK: approval_request.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    private ApprovalRequestEntity approvalRequest;

    // 결재자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "approval_id", nullable = false)
    private MemberEntity approver;

    @Column(nullable = false)
    private Integer sequence; // 결재 순서

    @Column(nullable = false, length = 20)
    private String status; // 결재 상태

    @Column(name = "approved_dt")
    private LocalDateTime approvedDt; // 결재 일시
}
