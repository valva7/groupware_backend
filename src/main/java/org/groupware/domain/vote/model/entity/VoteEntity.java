package org.groupware.domain.vote.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vote")
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 투표 생성자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "creator_id", nullable = false)
    private MemberEntity creator;

    @Column(name = "type", nullable = false, length = 50)
    private String type; // 투표 유형 (단일, 다중 등)

    @Column(name = "title", nullable = false, length = 255)
    private String title; // 투표 제목

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // 투표 설명

    @Column(name = "target_department", length = 255)
    private String targetDepartment; // 대상 부서

    @Column(name = "status", nullable = false, length = 50)
    private String status; // 상태 (진행 중, 종료 등)

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 시작일

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // 종료일
}
