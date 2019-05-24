package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidUserSummary;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserSummaryValidityChecker implements ConstraintValidator<ValidUserSummary, UserSummary> {

    @Override
    public boolean isValid(UserSummary userSummary, ConstraintValidatorContext context) {
        return !(((userSummary.getState() == 2 || userSummary.getState() == 3)
                && (userSummary.getType() == 1 || userSummary.getDesignation() == 1))
                || (userSummary.getType() == 1 && userSummary.getDesignation() != 1)
                || (userSummary.getType() != 1 && userSummary.getDesignation() == 1));
    }
}
