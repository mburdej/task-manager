package org.example.manager;

import org.example.task.Category;
import org.example.task.Priority;
import org.example.task.Task;

import java.util.List;

public interface TaskManager {

    void add(Task task);

    List<Task> getAll();

    void sortByPriority();

    List<Task> getDistinctTasks();

    List<Task> getCompletedTasks();

    List<Task> getTasksWithCategory(Category category);

    List<Task> getTasksWithPriority(Priority priority);

    List<String> getNames();

    boolean checkIfAllTasksLongerThenOneSymbol();
}
