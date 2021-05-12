package com.healthcare.team.commons;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class ValidationsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyFieldShouldThrow() {
        Validations.isValidGender(" ");
        Validations.isValidState(" ");
        Validations.isValidModule(" ");
    }

    @Test(expected = NullPointerException.class)
    public void testNullFieldShouldThrow() {
        Validations.isValidGender(null);
        Validations.isValidState(null);
        Validations.isValidModule(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFieldShouldThrow() {
        Validations.isValidGender("man");
        Validations.isValidState("Bucharest");
        Validations.isValidModule("Soul pain");
    }

    @Test(timeout = 15000)
    public void checkInvalidGender() {
        List<String> genders = Validations.validGenders;
        assertThat(genders, hasItems("female", "male", "both"));
        assertThat(genders, not(IsEmptyCollection.empty()));
        assertEquals(Optional.of("female"), genders.stream().findFirst());
    }
}
