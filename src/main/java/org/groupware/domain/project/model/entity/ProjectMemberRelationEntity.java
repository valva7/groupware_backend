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
@Table(name = "project_member_relation")
public class ProjectMemberRelationEntity extends TimeBaseEntity {

    @EmbeddedId
    private MemberProjectId id;

    // 복합키 : project.id, member.id
    // (FK: project.id)
    // MemberProjectId.memberId 매핑
    @MapsId("projectId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    // (FK: member.id)
    // MemberProjectId.projectId
    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(length = 10)
    private String role; // 역할

    // 프로젝트 권한 - 비트값으로 권한 관리
    @Column(name = "permission_bit", nullable = false)
    private Integer permissionBit;

    @Column(name = "joined_at", nullable = false)
    private LocalDate joinedAt; // 참여일
}
