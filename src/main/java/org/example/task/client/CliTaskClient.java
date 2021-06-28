package org.example.task.client;

import lombok.RequiredArgsConstructor;
import org.example.task.client.command.Command;
import org.example.task.client.command.UnauthorizedCommandException;
import org.example.task.client.command.UnsupportedCommandException;
import org.example.task.io.ReadWriter;
import org.example.task.loader.TaskLoader;
import org.example.task.manager.TaskManager;
import org.example.task.model.*;
import org.example.user.StringIDUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.example.task.client.command.Command.*;

@RequiredArgsConstructor
public class CliTaskClient implements TaskClient {

    private final ClientContext context;

    private final TaskManager manager;

    private final TaskLoader loader;

    private final ReadWriter rw;

    @Override
    public Command enterCommand() {
        this.rw.write("Enter command: ");
        return Command.fromString(this.rw.readLine());
    }

    @Override
    public void processCommand(Command command) {
        if (!authorized(command)) {
            throw new UnauthorizedCommandException("unauthorized command: " + command.getValue());
        }
        switch (command) {
            case HELP:
                processHelpCommand();
                break;
            case LOGIN:
                processLoginCommand();
                break;
            case EXIT:
                processExitCommand();
                break;
            case SHOW_ALL:
                processShowAllCommand();
                break;
            case SHOW_SORTED_BY_PRIORITY:
                processShowSortedByPriorityCommand();
                break;
            case SHOW_DISTINCT:
                processShowDistinctCommand();
                break;
            case SHOW_COMPLETED:
                processShowCompletedCommand();
                break;
            case SHOW_BY_CATEGORY:
                processShowByCategoryCommand();
                break;
            case SHOW_BY_PRIORITY:
                processShowByPriorityCommand();
                break;
            case SHOW_NAMES:
                processShowNamesCommand();
                break;
            case CHECK_IF_LONGER_THEN_ONE_SYMBOL:
                processCheckOneSymbolCommand();
                break;
            case ADD_ONE_TIME_TASK:
                processAddOneTimeTaskCommand();
                break;
            case ADD_REPEATABLE_TASK:
                processAddRepeatableTaskCommand();
                break;
            default:
                throw new UnsupportedCommandException("unsupported command: " + command);
        }
    }

    private void processLoginCommand() {
        this.rw.write("Enter user id: ");
        String id = this.rw.readLine();
        this.rw.write("Enter username: ");
        String username = this.rw.readLine();

        StringIDUser user = StringIDUser.builder()
                .id(id)
                .username(username)
                .build();
        List<Task> userTasks = this.loader.load(user);

        this.manager.setTasks(userTasks);
        this.context.setCurrentLoggedInUser(user);
        this.context.setState(ClientContext.State.AUTHENTICATED);
    }

    private void processHelpCommand() {
        List<Command> allCommands = getAll();
        allCommands.forEach(cmd -> this.rw.writeLine(cmd.toString()));
    }

    private void processCheckOneSymbolCommand() {
        this.rw.writeLine("All tasks are longer then one symbol? " + this.manager.checkIfAllTasksLongerThenOneSymbol());
    }

    private void processShowNamesCommand() {
        this.rw.writeLine("Task names: " + this.manager.getNames());
    }

    private void processShowByPriorityCommand() {
        this.rw.write("Enter priority: ");
        String priorityStr = this.rw.readLine();
        List<Task> tasks = this.manager.getTasksWithPriority(Priority.fromInt(Integer.parseInt(priorityStr)));
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processShowByCategoryCommand() {
        this.rw.write("Enter category: ");
        List<Task> tasks = this.manager.getTasksWithCategory(Category.fromString(this.rw.readLine()));
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processShowCompletedCommand() {
        List<Task> tasks = this.manager.getCompletedTasks();
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processShowDistinctCommand() {
        List<Task> tasks = this.manager.getDistinctTasks();
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processShowSortedByPriorityCommand() {
        List<Task> tasks = this.manager.sortByPriority();
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processAddRepeatableTaskCommand() {
        RepeatableTask task = new RepeatableTask();
        fillCommonTaskFields(task);
        this.rw.write("Enter interval: ");
        task.setInterval(this.rw.readLine());
        this.manager.add(task);
    }

    private void processAddOneTimeTaskCommand() {
        OneTimeTask task = new OneTimeTask();
        fillCommonTaskFields(task);
        this.rw.write("Enter execution date (yyyy-MM-dd HH:mm): ");
        task.setExecutionDate(LocalDateTime.parse(this.rw.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        this.manager.add(task);
    }

    private void processShowAllCommand() {
        List<Task> tasks = this.manager.getTasks();
        tasks.forEach(task -> this.rw.writeLine(task.toString()));
    }

    private void processExitCommand() {
        if (authenticated()) {
            this.loader.save(this.context.getCurrentLoggedInUser(), manager.getTasks());
        }
        this.context.setState(ClientContext.State.FINISHED);
    }

    private boolean authorized(Command command) {
        if (command == LOGIN || command == EXIT || command == HELP) {
            return true;
        }
        return authenticated();
    }

    private boolean authenticated() {
        return this.context.getState() == ClientContext.State.AUTHENTICATED;
    }

    private void fillCommonTaskFields(Task task) {
        this.rw.write("Enter name: ");
        task.setName(this.rw.readLine());
        this.rw.write("Enter category: ");
        task.setCategory(Category.fromString(this.rw.readLine()));
        this.rw.write("Enter priority: ");
        String priorityStr = this.rw.readLine();
        task.setPriority(Priority.fromInt(Integer.parseInt(priorityStr)));
    }
}

