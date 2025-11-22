package org.groupware.domain.auth.repository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.groupware.domain.auth.model.BaseRole;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRepositoryImpl implements RoleRepository{

    private final JpaRoleRepository jpaRoleRepository;

    public List<BaseRole> findRoleList() {
        return jpaRoleRepository.findAll().stream()
                .map(entity -> BaseRole.builder()
                        .roleId(entity.getRoleId())
                        .roleName(entity.getRoleName())
                        .build()
                )
                .toList();
    }


}
