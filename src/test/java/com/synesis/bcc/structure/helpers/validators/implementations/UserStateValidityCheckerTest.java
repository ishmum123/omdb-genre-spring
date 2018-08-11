package com.synesis.bcc.structure.helpers.validators.implementations;

import com.synesis.bcc.structure.database.entities.UserState;
import com.synesis.bcc.structure.database.repositories.UserStateRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserStateValidityCheckerTest {

    private UserStateRepository repository;
    private UserStateValidityChecker validityChecker;

    @Before
    public void setUp() {
        repository = mock(UserStateRepository.class);
        validityChecker = new UserStateValidityChecker(repository);
    }

    @Test
    public void isValid() {
        int unavailableId = 404;

        when(repository.findById(unavailableId)).thenReturn(Optional.empty());
        assertFalse(validityChecker.isValid(unavailableId, null));

        int availableId = 200;

        when(repository.findById(availableId)).thenReturn(Optional.of(new UserState()));
        assertTrue(validityChecker.isValid(availableId, null));
    }
}