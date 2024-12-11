package src.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public final class InputFileReader {

    private static InputFileReader instance;
    private BufferedReader br;

    private InputFileReader() {
        try {
            br = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("No input file!");
        }
    }

    public static InputFileReader getInstance() {
        if (instance == null)
            instance = new InputFileReader();
        return instance;
    }

    public static String readLine() {
        try {
            return getInstance().br.readLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public static String[] readAllLines() {
        ArrayList<String> strings = new ArrayList<>();
        String s = readLine();
        while (s != null) {
            strings.add(s);
            s = readLine();

        }
        String[] ret = new String[strings.size()];
        ret = strings.toArray(ret);
        return ret;
    }

    public static String read() {
        return String.join("\n", readAllLines());
    }
}