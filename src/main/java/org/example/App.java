package org.example;

import org.example.task.client.CliTaskClient;
import org.example.task.client.ClientContext;
import org.example.task.client.command.Command;
import org.example.task.client.TaskClient;
import org.example.task.io.ReadWriter;
import org.example.task.io.StdReadWriter;
import org.example.task.loader.FileTaskLoader;
import org.example.task.loader.TaskLoader;
import org.example.task.manager.SimpleTaskManager;
import org.example.task.manager.TaskManager;

import static org.example.task.client.ClientContext.State.ANONYMOUS;
import static org.example.task.client.ClientContext.State.FINISHED;

public class App {

    public static void main(String[] args) {
        TaskManager manager = new SimpleTaskManager();
        TaskLoader loader = new FileTaskLoader();
        ReadWriter rw = new StdReadWriter();
        ClientContext context = new ClientContext(ANONYMOUS);
        TaskClient client = new CliTaskClient(context, manager, loader, rw);
        while (context.getState() != FINISHED) {
            try {
                Command command = client.enterCommand();
                client.processCommand(command);
            } catch (Exception ex) {
                rw.writeLine("Error occurred: " + ex.getMessage());
            }
            rw.writeLine();
        }
    }
}
