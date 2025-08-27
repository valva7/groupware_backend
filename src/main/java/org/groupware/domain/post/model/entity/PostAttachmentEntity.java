package org.groupware.domain.post.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post_attachment")
public class PostAttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 게시글 (FK: post.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private PostEntity post;

    @Column(name = "file_name", nullable = false, length = 255)
    private String fileName; // 파일명

    @Column(name = "file_path", nullable = false, length = 500)
    private String filePath; // 저장 경로

    @Column(name = "file_size", nullable = false)
    private Long fileSize; // 파일 크기 (byte 단위)
}
