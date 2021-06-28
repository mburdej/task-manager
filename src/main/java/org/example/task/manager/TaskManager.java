package org.example.task.manager;

import org.example.task.model.Category;
import org.example.task.model.Priority;
import org.example.task.model.Task;

import java.util.List;

public interface TaskManager {

    void setTasks(List<Task> tasks);

    void add(Task task);

    List<Task> getTasks();

    List<Task> sortByPriority();

    List<Task> getDistinctTasks();

    List<Task> getCompletedTasks();

    List<Task> getTasksWithCategory(Category category);

    List<Task> getTasksWithPriority(Priority priority);

    List<String> getNames();

    boolean checkIfAllTasksLongerThenOneSymbol();
}
