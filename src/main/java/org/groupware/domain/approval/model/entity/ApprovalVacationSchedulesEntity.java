package org.groupware.domain.approval.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "approval_vation_schedules")
public class ApprovalVacationSchedulesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // FK: approval_request.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "request_id", nullable = false)
    private ApprovalRequestEntity approvalRequestEntity;

    @Column(nullable = false)
    private LocalDate startDt; // 시작일

    @Column
    private LocalDate endDt; // 종료일

    @Column
    private LocalTime startTime; // 시차 (시작 시간)

    @Column
    private LocalTime endTime; // 시차 (종료 시간)

    @Column
    private String reason; // 휴가 사유

    @Column(nullable = false)
    private Integer sequence; // 일정 순서

}
