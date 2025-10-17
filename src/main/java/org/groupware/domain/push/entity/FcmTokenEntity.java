package org.groupware.domain.push.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="fcm_token")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class FcmTokenEntity {
    @Id
    private Long userId;

    private String token;

    @Column(name = "push_yn", nullable = false)
    private Boolean pushYn; // 푸시 알림 수신 여부

    public FcmTokenEntity(Long userId, String token) {
        this.userId = userId;
        this.token = token;
        this.pushYn = true;
    }

}