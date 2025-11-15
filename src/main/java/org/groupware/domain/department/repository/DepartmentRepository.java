package org.groupware.domain.department.repository;

import java.util.List;
import java.util.Optional;
import org.groupware.domain.department.model.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    boolean existsByCode(String departmentCode);

    Optional<DepartmentEntity> findByCode(String departmentCode);

    Optional<List<DepartmentEntity>> findAllBy();

}
