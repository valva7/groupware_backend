package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_type_field")
public class ApprovalTypeFieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: approval_type.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private ApprovalTypeEntity approvalType;

    @Column(name = "field_name", nullable = false, length = 100)
    private String fieldName; // 입력 항목 이름

    @Column(name = "field_type", nullable = false, length = 20)
    private String fieldType; // 데이터 타입 (TEXT, NUMBER, DATE 등)

    @Column(name = "required_yn", nullable = false)
    private Boolean requiredYn; // 필수 여부
}
