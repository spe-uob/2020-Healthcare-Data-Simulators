package com.healthcare.team.commons;

import java.util.List;
import java.util.stream.Stream;

public class Validations {

    public static final List<String> validGenders = List.of("female", "male", "both");

    public static void isValidGender(String gender) {
        validGenders.stream()
                .filter(gender::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, gender"));
    }

    public static void isValidState(String state) {
          Stream.of(States.values())
                .map(States::getName)
                .filter(state::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, state"));
    }

    public static void isValidModule(String module) {
        Stream.of(Modules.values())
                .map(Modules::getDescription)
                .filter(module::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, module"));
    }
}
