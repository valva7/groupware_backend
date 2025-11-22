package org.groupware.domain.auth.repository;

import java.util.List;
import org.groupware.domain.auth.model.BaseRole;

public interface RoleRepository {

    List<BaseRole> findRoleList();

}
