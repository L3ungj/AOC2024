package src.utils;

import java.util.stream.Stream;

public class Utils {
    // v ^ < >
    static public final int[] dirx = { 1, -1, 0, 0 };
    static public final int[] diry = { 0, 0, -1, 1 };
    // > ^ < v
    static public final PairInt[] dirs = { new PairInt(0, 1), new PairInt(-1, 0), new PairInt(0, -1),
            new PairInt(1, 0) };

    public static Integer[] splitMapInt(String s, String regex) {
        return Stream.of(s.split(regex)).map(Integer::parseInt).toArray(Integer[]::new);
    }

    public static boolean inBounds(int x, int y, int n, int m) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static boolean inBounds(PairInt p, int n, int m) {
        return 0 <= p.x && p.x < n && 0 <= p.y && p.y < m;
    }

}
