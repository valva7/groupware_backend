package org.groupware.domain.communication.post.model.entity;

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
@Table(name = "post")
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 작성자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private MemberEntity author;

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content; // 내용 (HTML)

    @Column(name = "category_code", length = 50)
    private String categoryCode; // 카테고리 코드 (POST_CATEGORY)

    @Column(name = "status_code", nullable = false, length = 50)
    private String statusCode; // 게시물 상태 코드 (POST_STATUS: NORM, REPT, RESL, DEL)

    @Column(name = "view_count", nullable = false)
    private Long viewCount = 0L; // 조회수

    @Column(name = "like_count", nullable = false)
    private Long likeCount = 0L; // 좋아요 수

    @Column(name = "comment_count", nullable = false)
    private Long commentCount = 0L; // 댓글 수

    @Column(name = "is_notice", nullable = false)
    private Boolean isNotice = false; // 공지사항 여부

    @Column(name = "is_pinned", nullable = false)
    private Boolean isPinned = false; // 상단 고정 여부

    @Column(name = "tags", length = 500)
    private String tags; // 태그 목록 (JSON 배열 또는 comma-separated)

    // 첨부파일 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostAttachmentEntity> attachments = new ArrayList<>();

    // 댓글 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostCommentEntity> comments = new ArrayList<>();

    // 좋아요 관계
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLikeEntity> likes = new ArrayList<>();
}
