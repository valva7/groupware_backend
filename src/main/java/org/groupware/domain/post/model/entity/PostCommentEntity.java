package org.groupware.domain.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_comment")
public class PostCommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 게시글 (FK: post.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    // 댓글 작성자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "commenter_id", nullable = false)
    private MemberEntity commenter;

    @Lob
    @Column(nullable = false)
    private String content; // 댓글 내용
}
