package org.groupware.domain.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "screen")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScreenEntity {

    @Id
    private String screenId;

    @Column(nullable = false)
    private String screenName;

    @Column(nullable = false)
    private String path;      // 프론트 라우팅 경로

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;

}
