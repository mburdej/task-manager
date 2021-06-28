package org.example.task.manager;

import org.example.task.model.Category;
import org.example.task.model.Priority;
import org.example.task.model.RepeatableTask;
import org.example.task.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class SimpleTaskManagerTest {

    @Test
    void test_setTasks() {
        TaskManager givenTaskManager = new SimpleTaskManager();
        givenTaskManager.setTasks(new ArrayList<>());

        Assertions.assertNotNull(givenTaskManager.getTasks());
    }

    @Test
    void test_add() {
        TaskManager givenTaskManager = new SimpleTaskManager();
        RepeatableTask givenTask = new RepeatableTask();
        givenTaskManager.setTasks(new ArrayList<>());
        givenTaskManager.add(givenTask);

        Assertions.assertTrue(givenTaskManager.getTasks().contains(givenTask));
    }


    @Test
    void test_getTasks() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(new RepeatableTask());

        givenTaskManager.setTasks(givenTasks);

        Assertions.assertEquals(givenTasks, givenTaskManager.getTasks());
    }

    @Test
    void test_sortByPriority() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setPriority(Priority.fromInt(2));

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setPriority(Priority.fromInt(1));

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask2);
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        List<Task> actualTasks = givenTaskManager.sortByPriority();
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(givenTask1);
        expectedTasks.add(givenTask2);

        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void test_getDistinctTasks() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setPriority(Priority.fromInt(1));

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setPriority(Priority.fromInt(1));

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask2);
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        List<Task> actualTasks = givenTaskManager.getDistinctTasks();
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(givenTask1);

        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void getCompletedTasks() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setCompleted(true);

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setCompleted(false);

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask2);
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        List<Task> actualTasks = givenTaskManager.getCompletedTasks();
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(givenTask1);

        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void getTasksWithCategory() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setCategory(Category.WORK);

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setCategory(Category.HOME);

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask2);
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        List<Task> actualTasks = givenTaskManager.getTasksWithCategory(Category.WORK);
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(givenTask1);

        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void getTasksWithPriority() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setPriority(Priority.HIGH);

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setPriority(Priority.LOW);

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask2);
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        List<Task> actualTasks = givenTaskManager.getTasksWithPriority(Priority.HIGH);
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(givenTask1);

        Assertions.assertEquals(expectedTasks, actualTasks);
    }

    @Test
    void getNames() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setName("task1");

        RepeatableTask givenTask2 = new RepeatableTask();
        givenTask2.setName("task2");

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask1);
        givenTasks.add(givenTask2);
        givenTaskManager.setTasks(givenTasks);

        List<String> actualNames = givenTaskManager.getNames();
        List<String> expectedNames = new ArrayList<>();
        expectedNames.add("task1");
        expectedNames.add("task2");

        Assertions.assertEquals(expectedNames, actualNames);
    }

    @Test
    void test_checkIfAllTasksLongerThenOneSymbol_true() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setName("task1");

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        boolean actualCheck = givenTaskManager.checkIfAllTasksLongerThenOneSymbol();

        Assertions.assertTrue(actualCheck);
    }

    @Test
    void test_checkIfAllTasksLongerThenOneSymbol_false() {
        TaskManager givenTaskManager = new SimpleTaskManager();

        RepeatableTask givenTask1 = new RepeatableTask();
        givenTask1.setName("1");

        ArrayList<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask1);
        givenTaskManager.setTasks(givenTasks);

        boolean actualCheck = givenTaskManager.checkIfAllTasksLongerThenOneSymbol();

        Assertions.assertFalse(actualCheck);
    }
}