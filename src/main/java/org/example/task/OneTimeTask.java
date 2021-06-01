package org.example.task;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class OneTimeTask extends Task {

    private String executionDate;

    @Override
    public void execute() {
        System.out.println("hello from one time task");
    }
}
