package org.groupware.domain.vacation.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacation_request")
public class VacationRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: member.id (휴가 신청자)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    // 휴가 종류 (enum 관리 권장)
    @Column(name = "vacation_type", nullable = false)
    private String vacationType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate; // 시작일

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate; // 종료일

    @Column(nullable = false)
    private Double days; // 총 일수 (예: 0.5 = 반차, 1.0 = 하루)
}