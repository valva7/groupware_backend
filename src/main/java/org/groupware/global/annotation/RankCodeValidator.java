package org.groupware.global.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.groupware.domain.common.repository.CommonCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RankCodeValidator implements ConstraintValidator<ValidRank, String> {

    private final CommonCodeRepository commonCodeRepository;

    @Autowired
    public RankCodeValidator(CommonCodeRepository commonCodeRepository) {
        this.commonCodeRepository = commonCodeRepository;
    }

    @Override
    public boolean isValid(String rank, ConstraintValidatorContext context) {
        if (rank == null || rank.isEmpty()) {
            return false;
        }

        // DB에서 'POSITION' 그룹 코드 안에 value가 있는지 확인
        return commonCodeRepository.existsByGroupCodeAndCode("POSITION", rank);
    }
}
