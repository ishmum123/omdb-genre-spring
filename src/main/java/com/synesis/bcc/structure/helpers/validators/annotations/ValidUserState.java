package com.synesis.bcc.structure.helpers.validators.annotations;

import com.synesis.bcc.structure.helpers.validators.implementations.UserStateValidityChecker;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserStateValidityChecker.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserState {
    String message() default "{com.synesis.bcc.structure.message.ValidUserState}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
