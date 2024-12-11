package src.q6;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import src.utils.InputFileReader;

public class Q6b {
    static ImmutablePair<Integer, Integer> findArrow(char[][] a) {
        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < a[0].length; ++j)
                if (a[i][j] == '^')
                    return new ImmutablePair<>(i, j);
        return null;
    }

    static boolean isCyclic(char[][] a, int x, int y) {
        Set<Triple<Integer, Integer, Integer>> s = new TreeSet<>(); // x, y, dx + 10*dy
        int dx = -1, dy = 0;
        s.add(new ImmutableTriple<Integer, Integer, Integer>(x, y, dx + 10 * dy));
        while (true) {
            int nx = x + dx, ny = y + dy;
            if (!(0 <= nx && nx < a.length && 0 <= ny && ny < a[0].length))
                break;
            if (a[nx][ny] == '#') {
                int tdx = dx;
                dx = dy;
                dy = -tdx;
                continue;
            }
            x = nx;
            y = ny;
            ImmutableTriple<Integer, Integer, Integer> cur = new ImmutableTriple<Integer, Integer, Integer>(x, y,
                    dx + 10 * dy);
            if (s.contains(cur))
                return true;
            s.add(cur);
        }
        return false;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        char[][] a = Stream.of(lines).map(String::toCharArray).toArray(char[][]::new);
        Set<Pair<Integer, Integer>> s = new TreeSet<>();
        Pair<Integer, Integer> st = findArrow(a);
        int x = st.getLeft(), y = st.getRight();
        int sx = x, sy = y;
        a[x][y] = '.';
        s.add(st);
        int dx = -1, dy = 0;
        while (true) {
            int nx = x + dx, ny = y + dy;
            if (!(0 <= nx && nx < a.length && 0 <= ny && ny < a[0].length))
                break;
            if (a[nx][ny] == '#') {
                int tdx = dx;
                dx = dy;
                dy = -tdx;
                continue;
            }
            x = nx;
            y = ny;
            s.add(new ImmutablePair<>(x, y));
        }
        int ans = 0;
        for (Pair<Integer, Integer> p : s) {
            int px = p.getLeft(), py = p.getRight();
            if (px == sx && py == sy)
                continue;
            a[px][py] = '#';
            if (isCyclic(a, sx, sy))
                ++ans;
            a[px][py] = '.';
        }
        System.out.println(ans);
    }
}
