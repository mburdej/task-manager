package org.example.task.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Category {

    HOME("home"),
    WORK("work"),
    SPORT("sport");
    // ... add new categories there

    private final String value;

    public static Category fromString(String value) {
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedCategoryException("unknown category: " + value + ". available values: " + Arrays.toString(values())));
    }
}
