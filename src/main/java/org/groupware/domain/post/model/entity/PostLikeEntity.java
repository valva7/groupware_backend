package org.groupware.domain.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.member.model.entity.MemberEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_like")
public class PostLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 게시글 (FK: post.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    // 좋아요 누른 사람 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "liker_id", nullable = false)
    private MemberEntity liker;

    @Column(name = "first_like_yn", nullable = false)
    private Boolean firstLikeYn; // 최초 좋아요 여부

    @Column(name = "like_yn", nullable = false)
    private Boolean likeYn; // 현재 좋아요 여부
}
