package org.groupware.domain.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 프로젝트 종류 (FK: project_type.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private ProjectTypeEntity projectType;

    // 프로젝트 리더 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "leader_id", nullable = false)
    private MemberEntity leader;

    @Column(nullable = false, length = 200)
    private String title; // 프로젝트 제목

    @Column(length = 500)
    private String description; // 설명

    @Column(nullable = false, length = 50)
    private String status; // 진행 상태

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 시작일

    @Column(name = "end_date")
    private LocalDate endDate; // 종료일
}
