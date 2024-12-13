package src.q8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import src.utils.InputFileReader;

public class Q8b {
    static List<Pair<Integer, Integer>> antinode(Pair<Integer, Integer> a, Pair<Integer, Integer> b, int n, int m) {
        int x1 = a.getLeft(), x2 = b.getLeft();
        int y1 = a.getRight(), y2 = b.getRight();
        List<Pair<Integer, Integer>> ret = new ArrayList<>();
        for(int d = 0; ; ++d) {
            int x = x1 + (x2-x1) * d;
            int y = y1 + (y2-y1) * d;
            if(x < 0 || x >= n || y < 0 || y >= m) break;
            ret.add(new ImmutablePair<Integer,Integer>(x, y));
        }
        for(int d = -1; ; --d) {
            int x = x1 + (x2-x1) * d;
            int y = y1 + (y2-y1) * d;
            if(x < 0 || x >= n || y < 0 || y >= m) break;
            ret.add(new ImmutablePair<Integer,Integer>(x, y));
        }
        return ret;
    }

    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        Map<Character, List<Pair<Integer, Integer>>> mp = new TreeMap<>();
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == '.')
                    continue;
                if (mp.get(grid[i][j]) == null)
                    mp.put(grid[i][j], new ArrayList<>());
                mp.get(grid[i][j]).add(new ImmutablePair<Integer, Integer>(i, j));
            }
        Set<Pair<Integer, Integer>> st = new TreeSet<>();
        mp.forEach((k, list) -> {
            int ln = list.size();
            for (int i = 0; i < ln; ++i) {
                // System.out.println(String.format("%c %d %d", k, list.get(i).getLeft(), list.get(i).getRight()));
                for (int j = i + 1; j < ln; ++j) {
                    antinode(list.get(i), list.get(j), n, m).forEach(st::add);
                }
            }
        });
        System.out.println(st.size());
    }
}
