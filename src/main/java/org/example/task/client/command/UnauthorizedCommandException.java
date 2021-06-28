package org.example.task.client.command;

public class UnauthorizedCommandException extends RuntimeException {

    public UnauthorizedCommandException(String message) {
        super(message);
    }
}
