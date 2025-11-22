package org.groupware.domain.department.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.groupware.domain.department.model.Department;
import org.groupware.domain.department.model.entity.DepartmentEntity;
import org.groupware.domain.department.repository.JpaDepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Transactional(readOnly = true)
    public List<Department> findDepartmentList() {
        Optional<List<DepartmentEntity>> departmentEntities = jpaDepartmentRepository.findAllBy();

        return departmentEntities
                .map(list -> list.stream()
                .map(DepartmentEntity::toDepartment)
                .toList())
            .orElse(Collections.emptyList());
    }


}
