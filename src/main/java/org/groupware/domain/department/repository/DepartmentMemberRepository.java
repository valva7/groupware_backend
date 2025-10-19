package org.groupware.domain.department.repository;

import org.groupware.domain.department.model.entity.DepartmentMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentMemberRepository extends JpaRepository<DepartmentMemberEntity, Long> {


}
