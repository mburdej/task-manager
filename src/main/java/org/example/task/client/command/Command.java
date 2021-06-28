package org.example.task.client.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.task.model.UnsupportedCategoryException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Command {

    HELP("help", "show all available commands"),
    LOGIN("login", "authenticate user"),
    EXIT("exit", "exit & save"),
    SHOW_ALL("show all", "show all user tasks"),
    SHOW_SORTED_BY_PRIORITY("sort by priority", "show sorted user tasks by priority"),
    SHOW_DISTINCT("show distinct", "show unique user tasks"),
    SHOW_COMPLETED("show completed", "show completed user tasks"),
    SHOW_BY_CATEGORY("show by category", "show user tasks with specified category"),
    SHOW_BY_PRIORITY("show by priority", "show user tasks with specified priority"),
    SHOW_NAMES("show names", "show user task names"),
    CHECK_IF_LONGER_THEN_ONE_SYMBOL("check if longer then one symbol", "verify if all user tasks names are longer then one symbol"),
    ADD_ONE_TIME_TASK("add one time task", "add one time task to user task list"),
    ADD_REPEATABLE_TASK("add repeatable task", "add repeatable task to user task list");

    private final String value;

    private final String description;

    public static Command fromString(String value) {
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new UnsupportedCategoryException("unknown command: " + value));
    }

    public static List<Command> getAll() {
        return Arrays.stream(values())
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return value + "\t-\t" + description;
    }
}
