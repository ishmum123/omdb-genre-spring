package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.database.entities.Designation;
import com.newscred.omdb.genre.database.repositories.DesignationRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DesignationValidityCheckerTest {

    private DesignationRepository repository;
    private DesignationValidityChecker validityChecker;

    @Before
    public void setUp() {
        repository = mock(DesignationRepository.class);
        validityChecker = new DesignationValidityChecker(repository);
    }

    @Test
    public void isValid() {
        int unavailableId = 404;

        when(repository.findById(unavailableId)).thenReturn(Optional.empty());
        assertFalse(validityChecker.isValid(unavailableId, null));

        int availableId = 200;

        when(repository.findById(availableId)).thenReturn(Optional.of(new Designation()));
        assertTrue(validityChecker.isValid(availableId, null));
    }
}