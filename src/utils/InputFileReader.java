package src.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;

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
        return getInstance().br.lines().toArray(String[]::new);
    }

    public static String read() {
        // maybe this works?
        // return getInstance().br.lines().reduce((acc, s) -> (acc += "\n" + s)).get();
        return String.join("\n", readAllLines());
    }

    public static char[][] readGrid() {
        return getInstance().br.lines().map(String::toCharArray).toArray(char[][]::new);
    }
}