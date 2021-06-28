package org.example.task.io;

import java.io.PrintStream;
import java.util.Scanner;

public class StdReadWriter implements ReadWriter {

    private final PrintStream out = System.out;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void writeLine() {
        out.println();
    }

    @Override
    public void writeLine(String str) {
        out.println(str);
    }

    @Override
    public void write(String str) {
        out.print(str);
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
