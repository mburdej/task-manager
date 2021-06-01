package org.example;

import org.example.manager.BasicTaskManager;
import org.example.manager.TaskManager;
import org.example.task.Category;
import org.example.task.OneTimeTask;
import org.example.task.Priority;
import org.example.task.RepeatableTask;
import org.example.user.User;

public class Main {

    public static void main(String[] args) {

        TaskManager basicTaskManager = new BasicTaskManager();

        var oneTimeTask1 = new OneTimeTask();
        oneTimeTask1.setName("one-time-task-name");
        oneTimeTask1.setCategory(Category.SPORT);
        oneTimeTask1.setPriority(Priority.MEDIUM);
        oneTimeTask1.setExecutionDate("21-05-2021");
        basicTaskManager.add(oneTimeTask1);

        var oneTimeTask2 = new OneTimeTask();
        oneTimeTask2.setName("one-time-task-name");
        oneTimeTask2.setCategory(Category.SPORT);
        oneTimeTask2.setPriority(Priority.MEDIUM);
        oneTimeTask2.setExecutionDate("21-05-2021");
        basicTaskManager.add(oneTimeTask2);

        var repeatableTask = new RepeatableTask();
        repeatableTask.setName("r");
        repeatableTask.setCategory(Category.HOME);
        repeatableTask.setInterval("every monday");
        repeatableTask.setPriority(Priority.HIGH);
        repeatableTask.setCompleted(true);
        basicTaskManager.add(repeatableTask);

        basicTaskManager.sortByPriority();
        System.out.println("All tasks: " + basicTaskManager.getAll());
        System.out.println("Distinct tasks: " + basicTaskManager.getDistinctTasks());
        System.out.println("Tasks with sport category: " + basicTaskManager.getTasksWithCategory(Category.SPORT));
        System.out.println("Tasks with medium priority: " + basicTaskManager.getTasksWithPriority(Priority.MEDIUM));
        System.out.println("Completed tasks: " + basicTaskManager.getCompletedTasks());
        System.out.println("Are all tasks have name longer then one symbol? " + basicTaskManager.checkIfAllTasksLongerThenOneSymbol());

        var userWithStringID = User.<String>builder()
                .id("1")
                .username("user-with-string-id")
                .build();
        System.out.println(userWithStringID);

        var userWithIntegerID = User.<Integer>builder()
                .id(2)
                .username("user-with-integer-id")
                .build();
        System.out.println(userWithIntegerID);
    }
}
