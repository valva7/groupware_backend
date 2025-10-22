package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu")
public class MenuEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 상위 메뉴 (self-reference)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuEntity parent;

    @Column(nullable = false, length = 100)
    private String name; // 메뉴명

    @Column(length = 200)
    private String path; // 라우팅 경로

    @Column(length = 100)
    private String icon; // 아이콘

    @Column(nullable = false)
    private Integer sequence; // 노출 순서

    @Column(nullable = false, length = 100)
    private String baseRole; // 기본 권한

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부
}
