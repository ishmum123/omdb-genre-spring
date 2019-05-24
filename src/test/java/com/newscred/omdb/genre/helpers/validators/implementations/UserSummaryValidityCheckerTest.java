package com.newscred.omdb.genre.helpers.validators.implementations;

import com.newscred.omdb.genre.helpers.dataclass.UserSummary;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserSummaryValidityCheckerTest {

    private UserSummaryValidityChecker validityChecker;

    @Before
    public void setUp() throws Exception {
        validityChecker = new UserSummaryValidityChecker();
    }

    @Test
    public void isValid() {
        UserSummary summary = new UserSummary();
        summary.setState(2);
        summary.setDesignation(1);
        assertFalse(validityChecker.isValid(summary, null));

        summary.setType(1);
        summary.setDesignation(2);
        assertFalse(validityChecker.isValid(summary, null));

        summary.setDesignation(1);
        summary.setState(1);
        assertTrue(validityChecker.isValid(summary, null));
    }
}