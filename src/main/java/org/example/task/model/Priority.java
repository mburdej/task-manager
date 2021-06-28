package org.example.task.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Priority {

    LOW(1), MEDIUM(2), HIGH(3);

    private final int priority;

    public static Priority fromInt(int value) {
        return Arrays.stream(values())
                .filter(c -> c.priority == value)
                .findFirst()
                .orElseThrow(() -> new UnsupportedPriorityException("unknown priority: " + value + ". available values: " + Arrays.toString(values())));
    }
}
