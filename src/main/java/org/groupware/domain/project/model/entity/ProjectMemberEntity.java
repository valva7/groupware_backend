package org.groupware.domain.project.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "project_member")
public class ProjectMemberEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 프로젝트 (FK: project.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;

    // 참여자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(length = 100)
    private String role; // 역할

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt; // 참여일
}
