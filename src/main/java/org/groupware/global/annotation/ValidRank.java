package org.groupware.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RankCodeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRank {
    String message() default "유효하지 않은 직급 코드입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
