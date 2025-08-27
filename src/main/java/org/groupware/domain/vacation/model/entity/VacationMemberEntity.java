package org.groupware.domain.vacation.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vacation_member")
public class VacationMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: member.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(name = "total_days", nullable = false)
    private Double totalDays; // 총 부여 휴가 일수

    @Column(name = "used_days", nullable = false)
    private Double usedDays; // 사용한 휴가 일수

    @Column(name = "used_hours", nullable = false)
    private Integer usedHours; // 사용한 휴가 시간 (시간 단위)

    @Column(name = "remaining_days", nullable = false)
    private Double remainingDays; // 잔여 휴가 일수

    @Column(name = "remaining_hours", nullable = false)
    private Integer remainingHours; // 잔여 휴가 시간 (시간 단위)
}