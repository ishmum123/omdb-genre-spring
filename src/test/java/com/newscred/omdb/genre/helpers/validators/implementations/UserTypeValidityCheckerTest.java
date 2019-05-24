package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.database.entities.UserType;
import com.newscred.omdb.genre.database.repositories.UserTypeRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserTypeValidityCheckerTest {

    private UserTypeRepository repository;
    private UserTypeValidityChecker validityChecker;

    @Before
    public void setUp() {
        repository = mock(UserTypeRepository.class);
        validityChecker = new UserTypeValidityChecker(repository);
    }

    @Test
    public void isValid() {
        int unavailableId = 404;

        when(repository.findById(unavailableId)).thenReturn(Optional.empty());
        assertFalse(validityChecker.isValid(unavailableId, null));

        int availableId = 200;

        when(repository.findById(availableId)).thenReturn(Optional.of(new UserType()));
        assertTrue(validityChecker.isValid(availableId, null));
    }
}