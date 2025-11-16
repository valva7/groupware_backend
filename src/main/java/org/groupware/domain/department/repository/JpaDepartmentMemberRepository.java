package org.groupware.domain.department.repository;

import org.groupware.domain.department.model.entity.DepartmentMemberEntity;
import org.groupware.domain.department.model.entity.DepartmentMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDepartmentMemberRepository extends JpaRepository<DepartmentMemberEntity, DepartmentMemberId> {


}
