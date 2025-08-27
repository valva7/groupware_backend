package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_request")
public class ApprovalRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 전자결재 종류 (FK: approval_type.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private ApprovalTypeEntity approvalType;

    // 요청자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requester_id", nullable = false)
    private MemberEntity requester;

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Column(name = "reject_reason", length = 500)
    private String rejectReason; // 반려 사유

    @Column(nullable = false, length = 20)
    private String status; // 상태 (진행 중, 승인, 반려 등)
}
