package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "asset")
public class AssetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 자산 카테고리 (공통코드 관리, 예: FK로 코드 테이블과 연동 가능)
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "sub_category_id")
    private Long subCategoryId;

    @Column(nullable = false, length = 200)
    private String name; // 자산/비품 이름

    @Column(name = "serial_number", length = 100)
    private String serialNumber; // 시리얼 번호

    @Column(length = 100)
    private String manufacturer; // 제조사

    @Column(name = "model_name", length = 100)
    private String modelName; // 모델명

    @Column(length = 500)
    private String description; // 설명

    @Column(nullable = false, length = 50)
    private String status; // 상태 (AVAILABLE, IN_USE, REPAIR, DISPOSED 등)

    @Column(name = "purchase_date")
    private LocalDate purchaseDate; // 구매일

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice; // 구매 금액

    @Column(length = 200)
    private String location; // 보관 위치

    // 담당자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private MemberEntity manager;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // 등록일시

    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 수정일시
}
