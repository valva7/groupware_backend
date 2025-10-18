package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.groupware.domain.common.repository.CommonCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DetailRoleValidator implements ConstraintValidator<ValidDetailRole, String> {

    private final CommonCodeRepository commonCodeRepository;

    @Autowired
    public DetailRoleValidator(CommonCodeRepository commonCodeRepository) {
        this.commonCodeRepository = commonCodeRepository;
    }

    @Override
    public boolean isValid(String detailRole, ConstraintValidatorContext context) {
        if (detailRole == null || detailRole.isEmpty()) {
            return true; // 선택 사항일 수 있음
        }
        return commonCodeRepository.existsByGroupCodeAndCode("DETAIL_ROLE", detailRole);
    }
}
