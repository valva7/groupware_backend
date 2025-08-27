package org.groupware.domain.common.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.groupware.domain.auth.model.entity.RoleEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu_role")
public class MenuRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // 메뉴 (FK: menu.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuEntity menu;

    // 역할 (FK: role.id)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;
}
