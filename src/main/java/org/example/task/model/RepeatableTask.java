package org.example.task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RepeatableTask extends Task {

    private String interval;

    @Override
    public String toString() {
        return "RepeatableTask {\n" +
                "\t" + "Name: " + getName() + "\n" +
                "\t" + "Priority: " + getPriority() + "\n" +
                "\t" + "Category: " + getCategory() + "\n" +
                "\t" + "Interval: " + interval + "\n" +
                "\t" + "Completed: " + isCompleted() + "\n" +
                "}\n";
    }
}

