package org.groupware.domain.auth.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.groupware.domain.auth.model.BaseRole;
import org.groupware.global.entity.TimeBaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "roles")
public class RolesEntity extends TimeBaseEntity {

    @Id
    @Column(nullable = false, columnDefinition = "VARCHAR(50)")
    private String roleId;

    @Column(nullable = false)
    private String roleName;

    @Column
    private String description;

    public BaseRole toRole() {
        return BaseRole.builder()
            .roleId(this.roleId)
            .roleName(this.roleName)
            .build();
    }
}