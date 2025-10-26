package org.groupware.domain.communication.wiki.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wiki_attachment")
public class WikiAttachmentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 위키 (FK: wiki.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wiki_id", nullable = false)
    private WikiEntity wiki;

    @Column(nullable = false, length = 255)
    private String name; // 파일명

    @Column(name = "original_name", nullable = false, length = 255)
    private String originalName; // 원본 파일명

    @Column(nullable = false)
    private Long size; // 파일 크기 (bytes)

    @Column(nullable = false, length = 100)
    private String type; // 파일 타입 (MIME type)

    @Column(nullable = false, length = 500)
    private String url; // 파일 URL
}
