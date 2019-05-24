package com.newscred.omdb.genre.helpers.validators.annotations;

import com.newscred.omdb.genre.helpers.validators.implementations.UserSummaryValidityChecker;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UserSummaryValidityChecker.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidUserSummary {
    String message() default "user data {invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
