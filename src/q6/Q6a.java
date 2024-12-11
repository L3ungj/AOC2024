package src.q6;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import src.utils.InputFileReader;

public class Q6a {
    static ImmutablePair<Integer, Integer> findArrow(char[][] a) {
        for (int i = 0; i < a.length; ++i)
            for (int j = 0; j < a[0].length; ++j)
                if (a[i][j] == '^')
                    return new ImmutablePair<>(i, j);
        return null;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        char[][] a = Stream.of(lines).map(String::toCharArray).toArray(char[][]::new);
        Set<Pair<Integer, Integer>> s = new TreeSet<>();
        Pair<Integer, Integer> st = findArrow(a);
        int x = st.getLeft(), y = st.getRight();
        a[x][y] = '.';
        s.add(st);
        int dx = -1, dy = 0;
        while(true) {
            int nx = x + dx, ny = y + dy;
            if(!(0 <= nx && nx < a.length && 0 <= ny && ny < a[0].length))
                break;
            if(a[nx][ny] == '#') {
                int tdx = dx;
                dx = dy;
                dy = -tdx;
                continue;
            }
            x = nx; y = ny;
            s.add(new ImmutablePair<>(x, y));
        }
        System.out.println(s.size());
    }
}
