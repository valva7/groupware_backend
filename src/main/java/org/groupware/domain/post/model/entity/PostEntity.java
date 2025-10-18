package org.groupware.domain.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class PostEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 게시글 종류 (FK: post_type.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "type_id", nullable = false)
    private PostTypeEntity postType;

    // 작성자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private MemberEntity author;

    @Column(nullable = false, length = 200)
    private String title; // 제목

    @Lob
    @Column(nullable = false)
    private String content; // 내용

    @Column(name = "view_count", nullable = false)
    private Long viewCount; // 조회수

    @Column(name = "status", nullable = false)
    private Long status; // 게시물 상태 (정상, 신고, 해결, 삭제)
}
