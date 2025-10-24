package org.groupware.domain.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
public class ProjectEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 200)
    private String title; // 프로젝트 제목

    @Column(nullable = false, length = 200)
    private String company; // 프로젝트 협력 회사 - 회사 목록을 db 로 뺄것인지?

    @Column(length = 500)
    private String description; // 설명

    @Column(nullable = false, length = 50)
    private String status; // 진행 상태

    @Column(name = "delete_yn", nullable = false, length = 1)
    private String deleteYn; // 삭제 여부

    @Column(name = "start_dt", nullable = false)
    private LocalDate startDt; // 시작일

    @Column(name = "end_dt")
    private LocalDate endDt; // 종료일
}
