package com.synesis.bcc.structure.helpers.validators.implementations;

import com.synesis.bcc.structure.helpers.dataclass.UserSummary;
import com.synesis.bcc.structure.helpers.validators.annotations.ValidUserSummary;
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
