package org.groupware.domain.approval.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_field_data")
public class ApprovalFieldDataEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 20)
    private String fieldName; // 입력 항목 이름

    @Column(nullable = false, length = 100)
    private String fieldValue; // 필드 값

    @Column(nullable = false, length = 20)
    private String fieldType; // 데이터 타입 (TEXT, NUMBER, DATE 등)

    @Column(nullable = false)
    private Integer sequence; // 항목 순서
}
