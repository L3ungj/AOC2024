package src.q16;

import java.util.PriorityQueue;

import src.utils.InputFileReader;
import src.utils.PairInt;
import src.utils.Utils;

class QuadInt implements Comparable<QuadInt> {
    int p, x, y, d;

    public QuadInt(int p, int x, int y, int d) {
        this.p = p;
        this.x = x;
        this.y = y;
        this.d = d;
    }

    public int compareTo(QuadInt o) {
        return Integer.compare(p, o.p);
    }
}

public class Q16a {
    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int n = grid.length, m = grid[0].length;
        PairInt start = new PairInt(), end = new PairInt();
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 'S')
                    start = new PairInt(i, j);
                if (grid[i][j] == 'E')
                    end = new PairInt(i, j);
            }
        boolean[][][] vis = new boolean[n][m][4];
        PriorityQueue<QuadInt> pq = new PriorityQueue<>();
        pq.add(new QuadInt(0, start.x, start.y, 0));
        while (!pq.isEmpty()) {
            QuadInt qi = pq.poll();
            if (vis[qi.x][qi.y][qi.d])
                continue;
            vis[qi.x][qi.y][qi.d] = true;
            if (qi.x == end.x && qi.y == end.y) {
                System.out.println(qi.p);
                return;
            }
            if (!vis[qi.x][qi.y][(qi.d + 1) % 4])
                pq.add(new QuadInt(qi.p + 1000, qi.x, qi.y, (qi.d + 1) % 4));
            if (!vis[qi.x][qi.y][(qi.d + 3) % 4])
                pq.add(new QuadInt(qi.p + 1000, qi.x, qi.y, (qi.d + 3) % 4));
            PairInt np = PairInt.add(new PairInt(qi.x, qi.y), Utils.dirs[qi.d]);
            if (Utils.inBounds(np, n, m) && grid[np.x][np.y] != '#' && !vis[np.x][np.y][qi.d])
                pq.add(new QuadInt(qi.p + 1, np.x, np.y, qi.d));
        }
    }
}
