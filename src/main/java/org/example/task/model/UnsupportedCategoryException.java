package org.example.task.model;

public class UnsupportedCategoryException extends IllegalArgumentException {

    public UnsupportedCategoryException(String s) {
        super(s);
    }
}
