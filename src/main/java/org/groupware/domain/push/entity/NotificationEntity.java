package org.groupware.domain.push.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import org.groupware.domain.member.model.entity.MemberEntity;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "notification")
public class NotificationEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 수신자 (FK: member.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id", nullable = false)
    private MemberEntity recipient;

    @Column(nullable = false, length = 50)
    private String type; // 알림 타입 (결재, 프로젝트, 게시판, 투표)

    @Column(nullable = false, length = 200)
    private String title; // 알림 제목

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message; // 알림 내용

    @Column(name = "read_yn", nullable = false)
    private Boolean readYn; // 읽음 여부

    @Column(name = "go_url", length = 500)
    private String goUrl; // 이동 URL
}
