package org.groupware.domain.auth.repository;

import java.util.Optional;
import org.groupware.domain.auth.model.entity.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoleRepository extends JpaRepository<RolesEntity, String> {

    Optional<RolesEntity> findById(String role);

    boolean existsByRoleName(String role);

}
