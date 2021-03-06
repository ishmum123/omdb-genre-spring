package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.database.repositories.DesignationRepository;
import com.newscred.omdb.genre.helpers.validators.annotations.ValidDesignation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class DesignationValidityChecker implements ConstraintValidator<ValidDesignation, Integer> {

    private final DesignationRepository repository;

    public DesignationValidityChecker(@Autowired DesignationRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean isValid(Integer id, ConstraintValidatorContext context) {
        return repository.findById(id).isPresent();
    }
}
