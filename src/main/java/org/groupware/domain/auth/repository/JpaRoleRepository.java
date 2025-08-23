package org.groupware.domain.auth.repository;

import java.util.Optional;
import org.groupware.domain.auth.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByRoleName(String role);

}
