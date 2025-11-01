package org.groupware.domain.vacation.model.entity;

import jakarta.persistence.*;
import java.time.LocalTime;
import lombok.*;

import java.time.LocalDate;
import org.groupware.domain.approval.model.entity.ApprovalRequestEntity;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacation_approved")
public class VacationApprovedEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: member.id (휴가 신청자)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDate startDt; // 시작일

    @Column
    private LocalDate endDt; // 종료일

    @Column
    private LocalTime startTime; // 시차 (시작 시간)

    @Column
    private LocalTime endTime; // 시차 (종료 시간)

}