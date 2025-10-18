package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "common_code")
public class CommonCodeEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(name = "group_code", nullable = false, length = 100)
    private String groupCode; // 코드 그룹

    @Column(nullable = false, length = 50)
    private String code; // 코드 값

    @Column(name = "code_name", nullable = false, length = 100)
    private String codeName; // 코드 이름

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부

    @Column(nullable = false)
    private Integer sequence; // 노출 순서
}
