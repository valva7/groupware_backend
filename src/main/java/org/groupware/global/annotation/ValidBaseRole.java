package org.groupware.global.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BaseRoleValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBaseRole {
    String message() default "유효하지 않은 기본 권한입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
