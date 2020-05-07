package input.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import input.InputReader;

public class WholeInputReaderImpl implements InputReader {

    private final Scanner scanner;

    public WholeInputReaderImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String getInput() {
        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            lines.add(line);
        }

        return String.join("\n", lines);
    }
}
