package com.healthcare.team.commons;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

public class StatesTest {

    @Test
    public void isValidStates() {
        assertEquals("Aberdeenshire", States.Aberdeenshire.name());
        assertEquals("West Midlands", States.WestMidlands.getName());
        List<String> states = Stream.of(States.values()).map(States::getName).collect(toList());
        assertThat(states, hasItems("Gloucestershire", "Somerset", "Shropshire"));
        assertThat(states, not(IsEmptyCollection.empty()));
    }

    @Test
    public void isNotValidState() {
        assertThat("Baicoi", not(States.NorthYorkshire.name()));
    }
}
