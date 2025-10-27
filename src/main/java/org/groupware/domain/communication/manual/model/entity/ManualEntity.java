package org.groupware.domain.communication.manual.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "manual")
public class ManualEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 작성자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private MemberEntity author;

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Column(length = 500)
    private String description; // 설명

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용 (HTML)

    @Column(name = "category_code", length = 50)
    private String categoryCode; // 카테고리 코드 (MANUAL: PROC, SYS, ETC)

    @Column(name = "target_department", length = 100)
    private String targetDepartment; // 대상 부서

    @Column(length = 50)
    private String version; // 버전

    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = true; // 공개 여부

    @Column(length = 50)
    private String difficulty; // 난이도 (beginner, intermediate, advanced)

    @Column(name = "estimated_time", length = 50)
    private String estimatedTime; // 예상 소요 시간

    @Column(name = "tags", length = 500)
    private String tags; // 태그 목록 (JSON 배열 또는 comma-separated)

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L; // 조회수

    @Column(name = "download_count", nullable = false)
    private Long downloadCount = 0L; // 다운로드 수

    // 첨부파일 관계
    @OneToMany(mappedBy = "manual", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ManualAttachmentEntity> attachments = new ArrayList<>();
}