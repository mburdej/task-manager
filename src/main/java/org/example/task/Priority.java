package org.example.task;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Priority {

    LOW(1), MEDIUM(2), HIGH(3);

    private final int priority;
}
