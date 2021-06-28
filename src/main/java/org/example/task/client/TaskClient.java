package org.example.task.client;

import org.example.task.client.command.Command;

public interface TaskClient {

    Command enterCommand();

    void processCommand(Command command);
}
