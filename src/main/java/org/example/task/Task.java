package org.example.task;

import lombok.Data;

@Data
public abstract class Task implements Executable, Comparable<Task> {

    private String name;

    private Category category;

    private Priority priority;

    private boolean completed;

    @Override
    public int compareTo(Task that) {
        return Integer.compare(this.priority.getPriority(), that.priority.getPriority());
    }
}
