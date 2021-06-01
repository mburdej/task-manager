package org.example.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RepeatableTask extends Task {

    private String interval;

    @Override
    public void execute() {
        System.out.println("hello from repeatable task");
    }
}

