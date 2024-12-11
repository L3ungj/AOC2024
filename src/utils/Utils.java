package src.utils;

import java.util.stream.Stream;

public class Utils {
    public static Integer[] splitMapInt(String s, String regex) {
        return Stream.of(s.split(regex)).map(Integer::parseInt).toArray(Integer[]::new);
    }
}
