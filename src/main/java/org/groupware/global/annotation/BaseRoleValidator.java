package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.groupware.domain.auth.repository.JpaRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BaseRoleValidator implements ConstraintValidator<ValidBaseRole, String> {

    private final JpaRoleRepository jpaRoleRepository;

    @Autowired
    public BaseRoleValidator(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public boolean isValid(String baseRole, ConstraintValidatorContext context) {
        if (baseRole == null || baseRole.isEmpty()) {
            return false;
        }
        return jpaRoleRepository.existsByRoleName(baseRole);
    }
}
