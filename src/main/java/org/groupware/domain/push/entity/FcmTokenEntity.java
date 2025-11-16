package org.groupware.domain.push.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Table(name="fcm_token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FcmTokenEntity extends TimeBaseEntity {
    @Id
    private String memberId;

    @Column(length = 1000)
    private String token;

    @Column(name = "approval_yn", nullable = false)
    private Boolean approvalYn; // 전자결재 알림 수신 여부

    @Column(name = "project_yn", nullable = false)
    private Boolean projectYn; // 프로젝트 관련 알림 수신 여부

    @Column(name = "post_yn", nullable = false)
    private Boolean postYn; // 게시판 알림 수신 여부

    @Column(name = "vote_yn", nullable = false)
    private Boolean voteYn; // 투표 알림 수신 여부

    public FcmTokenEntity(String memberId, String token) {
        this.memberId = memberId;
        this.token = token;
        this.approvalYn = true;
        this.projectYn = true;
        this.postYn = true;
        this.voteYn = true;
    }

}