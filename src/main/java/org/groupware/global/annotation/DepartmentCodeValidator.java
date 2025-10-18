package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.groupware.domain.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentCodeValidator implements ConstraintValidator<ValidDepartment, String> {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentCodeValidator(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public boolean isValid(String departmentCode, ConstraintValidatorContext context) {
        if (departmentCode == null || departmentCode.isEmpty()) {
            return false;
        }

        // 'DEPT' 그룹 코드 안에 value가 있는지 확인
        return departmentRepository.existsByCode(departmentCode);
    }
}
