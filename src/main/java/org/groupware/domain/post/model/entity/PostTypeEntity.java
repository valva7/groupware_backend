package org.groupware.domain.post.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_type")
public class PostTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    @Column(nullable = false, length = 100)
    private String name; // 게시글 종류명

    @Column(length = 255)
    private String description; // 설명

    @Column(name = "used_count", nullable = false)
    private Long usedCount; // 사용 횟수

    @Column(name = "active_yn", nullable = false)
    private Boolean activeYn; // 사용 여부

    @Column(nullable = false)
    private Integer sequence; // 노출 순서
}
