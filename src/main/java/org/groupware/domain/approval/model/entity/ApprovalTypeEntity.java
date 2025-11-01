package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_type")
public class ApprovalTypeEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 100)
    private String name; // 전자결재 종류명

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "used_count", nullable = false)
    private Long usedCount; // 사용 횟수

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부
}