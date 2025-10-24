package org.groupware.domain.project.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.global.entity.TimeBaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project_task")
public class ProjectTaskEntity extends TimeBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 프로젝트 (FK: project.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    // 작업
    @Column(nullable = false, length = 50)
    private String task;

    // 작업 내용
    @Column(name = "task_description", length = 50)
    private String taskDescription;

    // 작업 종류 (develop, doc, setting ...) enum 으로 관리할듯
    @Column(name = "task_type", length = 10)
    private String taskType;

    // 작업 진행 상태
    @Column(nullable = false, length = 1)
    private String status;

    @Column(name = "start_dt", nullable = false)
    private LocalDate startDt; // 시작일

    @Column(name = "end_dt")
    private LocalDate endDt; // 종료일
}
