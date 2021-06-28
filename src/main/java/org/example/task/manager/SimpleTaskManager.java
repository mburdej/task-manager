package org.example.task.manager;

import lombok.Getter;
import lombok.Setter;
import org.example.task.model.Category;
import org.example.task.model.Priority;
import org.example.task.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SimpleTaskManager implements TaskManager {

    private List<Task> tasks = new ArrayList<>();

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Sorts by priority in descending manner (from highest to lowest).
     */
    @Override
    public List<Task> sortByPriority() {
        return tasks.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getDistinctTasks() {
        return tasks.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksWithCategory(Category category) {
        return tasks.stream()
                .filter(task -> task.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Task> getTasksWithPriority(Priority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority().equals(priority))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getNames() {
        return tasks.stream()
                .map(Task::getName)
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkIfAllTasksLongerThenOneSymbol() {
        return tasks.stream()
                .allMatch(task -> task.getName() != null && task.getName().trim().length() > 1);
    }
}
