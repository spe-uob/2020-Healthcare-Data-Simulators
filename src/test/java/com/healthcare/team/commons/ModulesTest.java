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

public class ModulesTest {

    @Test
    public void isValidModules() {
        assertEquals("ColorectalCancer", Modules.ColorectalCancer.name());
        assertEquals("Food-Allergies", Modules.FoodAllergies.getDescription());
        List<String> modules = Stream.of(Modules.values()).map(Modules::getDescription).collect(toList());
        assertThat(modules, hasItems("Metabolic-Syndrome-Care", "Opioid-Addiction"));
        assertThat(modules, not(IsEmptyCollection.empty()));
    }

    @Test
    public void isNotValidModule() {
        assertThat("Cookie", not(Modules.ColorectalCancer.name()));
    }
}
