package org.groupware.domain.auth.repository;

import org.groupware.domain.auth.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByRole(String role);

}
