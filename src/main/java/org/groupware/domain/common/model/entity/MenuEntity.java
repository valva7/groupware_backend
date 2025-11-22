package org.groupware.domain.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.groupware.domain.auth.model.entity.RolesEntity;

@Entity
@Table(name = "menu")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuEntity {

    @Id
    private String menuId;

    @Column(nullable = false)
    private String menuName;      // 메뉴 이름

    @Column(nullable = false)
    private String path;      // 프론트 라우팅 경로

    @Column(nullable = false)
    private Integer sequence;      // 정렬 순서

    @Column(nullable = false)
    private Boolean activeYn; // 사용 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private MenuEntity parent;

    @OneToMany(mappedBy = "parent")
    private List<MenuEntity> children = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "menu_roles",
            joinColumns = @JoinColumn(name = "menu_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RolesEntity> allowedRoles = new ArrayList<>();

    @OneToMany(mappedBy = "menu")
    private List<ScreenEntity> screenEntities = new ArrayList<>();
}
