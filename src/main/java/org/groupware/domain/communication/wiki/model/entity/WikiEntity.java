package org.groupware.domain.communication.wiki.model.entity;

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
@Table(name = "wiki")
public class WikiEntity extends TimeBaseEntity {

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
    private String summary; // 요약

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용 (HTML)

    @Column(name = "category_code", length = 50)
    private String categoryCode; // 카테고리 코드 (WIKI_CATEGORY: WORK_GUIDE, SYS_MANUAL, PROCESS 등)

    // 부모 페이지 (FK: wiki.id) - 계층 구조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_page_id")
    private WikiEntity parentPage;

    @Column(name = "tags", length = 500)
    private String tags; // 태그 목록 (JSON 배열 또는 comma-separated)

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L; // 조회수

    @Column(nullable = false)
    private Integer version = 1; // 버전 번호

    // 하위 페이지 목록
    @OneToMany(mappedBy = "parentPage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WikiEntity> childPages = new ArrayList<>();

    // 첨부파일 관계
    @OneToMany(mappedBy = "wiki", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WikiAttachmentEntity> attachments = new ArrayList<>();
}
