package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "asset_allocation")
public class AssetAllocationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 자산 (FK: asset.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "asset_id", nullable = false)
    private AssetEntity asset;

    // 배정 대상자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private MemberEntity member;

    @Column(name = "assigned_date", nullable = false)
    private LocalDate assignedDate; // 배정일

    @Column(name = "returned_date")
    private LocalDate returnedDate; // 반납일 (NULL이면 사용 중)

    @Column(length = 500)
    private String note; // 특이사항
}
