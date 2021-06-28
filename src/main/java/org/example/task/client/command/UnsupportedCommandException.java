package org.example.task.client.command;

public class UnsupportedCommandException extends IllegalArgumentException {

    public UnsupportedCommandException(String s) {
        super(s);
    }
}
