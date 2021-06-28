package org.example.task.client;

import org.example.task.client.command.Command;
import org.example.task.io.ReadWriter;
import org.example.task.loader.TaskLoader;
import org.example.task.manager.TaskManager;
import org.example.task.model.OneTimeTask;
import org.example.task.model.Task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.example.task.client.command.Command.getAll;

@ExtendWith(MockitoExtension.class)
class CliTaskClientTest {

    @Mock
    private TaskManager taskManager;

    @Mock
    private TaskLoader taskLoader;

    @Spy
    private ClientContext clientContext = new ClientContext(ClientContext.State.ANONYMOUS);

    @Mock
    private ReadWriter readWriter;

    @InjectMocks
    private CliTaskClient taskClient;

    @Test
    void test_enterCommand() {
        Command expectedCommand = Command.HELP;
        Mockito.when(readWriter.readLine()).thenReturn("help");
        Command actualCommand = taskClient.enterCommand();

        Assertions.assertEquals(expectedCommand, actualCommand);
    }

    @Test
    void test_processCommand_help() {
        List<Command> allCommands = getAll();
        taskClient.processCommand(Command.HELP);

        Mockito.verify(readWriter, Mockito.times(allCommands.size())).writeLine(Mockito.anyString());
    }

    @Test
    void test_processCommand_login() {
        String givenID = "id";
        String givenName = "name";
        OneTimeTask givenTask = new OneTimeTask();
        List<Task> givenTasks = new ArrayList<>();
        givenTasks.add(givenTask);
        Mockito.when(readWriter.readLine()).thenReturn(givenID);
        Mockito.when(readWriter.readLine()).thenReturn(givenName);
        Mockito.when(taskLoader.load(Mockito.any())).thenReturn(givenTasks);

        taskClient.processCommand(Command.LOGIN);

        Mockito.verify(taskManager).setTasks(givenTasks);
        Mockito.verify(clientContext).setState(ClientContext.State.AUTHENTICATED);
    }

    @Test
    void test_processCommand_exit() {
        taskClient.processCommand(Command.EXIT);
        Assertions.assertEquals(ClientContext.State.FINISHED, clientContext.getState());
    }

    @Test
    void test_processCommand_showAll() {

    }

    @Test
    void test_processCommand_showSortedByPriority() {

    }

    @Test
    void test_processCommand_showDistinct() {

    }

    @Test
    void test_processCommand_showCompleted() {

    }

    @Test
    void test_processCommand_showByCategory() {

    }

    @Test
    void test_processCommand_showByPriority() {

    }

    @Test
    void test_processCommand_showByNames() {

    }

    @Test
    void test_processCommand_checkLength() {

    }

    @Test
    void test_processCommand_addOneTimeTask() {

    }

    @Test
    void test_processCommand_addRepeatableTask() {

    }
}