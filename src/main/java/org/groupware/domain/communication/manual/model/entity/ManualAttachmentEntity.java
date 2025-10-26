package org.groupware.domain.communication.manual.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "manual_attachment")
public class ManualAttachmentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 매뉴얼 (FK: manual.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "manual_id", nullable = false)
    private ManualEntity manual;

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
