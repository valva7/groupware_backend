package org.groupware.domain.project.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project_type")
public class ProjectTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 100)
    private String name; // 종류명

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부
}
