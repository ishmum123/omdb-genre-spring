package com.synesis.bcc.structure.helpers.validators.annotations;

import com.synesis.bcc.structure.helpers.validators.implementations.DesignationValidityChecker;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = DesignationValidityChecker.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDesignation {
    String message() default "{invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
