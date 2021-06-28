package org.example.task.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class OneTimeTask extends Task {

    private LocalDateTime executionDate;

    @Override
    public String toString() {
        String timeLeft;
        Duration diff = Duration.between(LocalDateTime.now(), executionDate);
        if (diff.isNegative()) {
            timeLeft = "-";
        } else {
            timeLeft = String.format("%d:%02d:%02d", diff.toHours(), diff.toMinutesPart(), diff.toSecondsPart());
        }
        return "OneTimeTask {\n" +
                "\t" + "Name: " + getName() + "\n" +
                "\t" + "Priority: " + getPriority() + "\n" +
                "\t" + "Category: " + getCategory() + "\n" +
                "\t" + "Execution date: " + executionDate + "\n" +
                "\t" + "Completed: " + isCompleted() + "\n" +
                "\t" + "Time left: " + timeLeft + "\n" +
                "}\n";
    }
}
