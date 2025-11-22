package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.groupware.domain.department.repository.JpaDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentCodeValidator implements ConstraintValidator<ValidDepartment, String> {

    private final JpaDepartmentRepository jpaDepartmentRepository;

    @Autowired
    public DepartmentCodeValidator(JpaDepartmentRepository jpaDepartmentRepository) {
        this.jpaDepartmentRepository = jpaDepartmentRepository;
    }

    @Override
    public boolean isValid(String departmentCode, ConstraintValidatorContext context) {
        if (departmentCode == null || departmentCode.isEmpty()) {
            return false;
        }

        // 'DEPT' 그룹 코드 안에 value가 있는지 확인
        return jpaDepartmentRepository.existsByCode(departmentCode);
    }
}
