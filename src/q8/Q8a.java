package src.q8;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import src.utils.InputFileReader;

public class Q8a {
    static Pair<Integer, Integer> antinode(Pair<Integer, Integer> a, Pair<Integer, Integer> b) {
        return new ImmutablePair<Integer, Integer>(2*a.getLeft() - b.getLeft(), 2*a.getRight() - b.getRight());
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
        Consumer<Pair<Integer, Integer>> myAdd = (p) -> {
            int x = p.getLeft(), y = p.getRight();
            if (0 <= x && x < n && 0 <= y && y < m)
                st.add(p);
        };
        mp.forEach((k, list) -> {
            int ln = list.size();
            for (int i = 0; i < ln; ++i) {
                // System.out.println(String.format("%c %d %d", k, list.get(i).getLeft(), list.get(i).getRight()));
                for (int j = i + 1; j < ln; ++j) {
                    myAdd.accept(antinode(list.get(i), list.get(j)));
                    myAdd.accept(antinode(list.get(j), list.get(i)));
                }
            }
        });
        System.out.println(st.size());
    }
}
