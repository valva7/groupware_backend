package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_type_data")
public class ApprovalTypeDataEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: approval_request.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    private ApprovalRequestEntity approvalRequestEntity;

    // FK: approval_field.id (전자결재 양식 항목 정의)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_field_id", nullable = false)
    private ApprovalTypeFieldEntity approvalTypeField;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String value; // 입력된 값

    @Column(nullable = false)
    private Integer sequence; // 항목 순서
}
