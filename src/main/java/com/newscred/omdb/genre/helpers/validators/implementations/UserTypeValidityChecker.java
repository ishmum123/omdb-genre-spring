package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.database.repositories.UserTypeRepository;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class UserTypeValidityChecker implements ConstraintValidator<ValidUserType, Integer> {

    private final UserTypeRepository repository;

    public UserTypeValidityChecker(@Autowired UserTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return repository.findById(value).isPresent();
    }
}
