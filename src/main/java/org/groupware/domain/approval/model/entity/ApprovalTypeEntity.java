package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_type")
public class ApprovalTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 상위 전자결재 종류 (self-reference)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ApprovalTypeEntity parent;

    @Column(nullable = false, length = 100)
    private String name; // 전자결재 종류명

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "used_count", nullable = false)
    private Long usedCount; // 사용 횟수

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부
}