package org.example.task.loader;

import org.example.task.model.Task;
import org.example.user.User;

import java.util.List;

public interface TaskLoader {

    List<Task> load(User<?> user);

    void save(User<?> user, List<Task> tasks);
}
