package src.q16;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeSet;

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

public class Q16b {
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
        ArrayList<int[]>[][][] parents = new ArrayList[n][m][4];
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j)
                for(int d= 0;d<4;++d)
                    parents[i][j][d] = new ArrayList<>();
        int[][][] dist = new int[n][m][4];
        while (!pq.isEmpty()) {
            QuadInt qi = pq.poll();
            if (vis[qi.x][qi.y][qi.d])
                continue;
            vis[qi.x][qi.y][qi.d] = true;
            dist[qi.x][qi.y][qi.d] = qi.p;
            if (!vis[qi.x][qi.y][(qi.d + 1) % 4])
                pq.add(new QuadInt(qi.p + 1000, qi.x, qi.y, (qi.d + 1) % 4));
            if (!vis[qi.x][qi.y][(qi.d + 3) % 4])
                pq.add(new QuadInt(qi.p + 1000, qi.x, qi.y, (qi.d + 3) % 4));
            PairInt np = PairInt.add(new PairInt(qi.x, qi.y), Utils.dirs[qi.d]);
            if (Utils.inBounds(np, n, m) && grid[np.x][np.y] != '#' && !vis[np.x][np.y][qi.d])
                pq.add(new QuadInt(qi.p + 1, np.x, np.y, qi.d));
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                for (int d = 0; d < 4; ++d) {
                    for(int dd = 1; dd <= 3; dd += 2) {
                        int nd = (d + dd) % 4;
                        if(dist[i][j][d] + 1000 == dist[i][j][nd]) {
                            parents[i][j][nd].add(new int[]{i, j, d});
                        }
                    }
                    PairInt np = PairInt.add(new PairInt(i, j), Utils.dirs[d]);
                    if (Utils.inBounds(np, n, m) && grid[np.x][np.y] != '#' && dist[i][j][d] + 1 == dist[np.x][np.y][d]) {
                        parents[np.x][np.y][d].add(new int[]{i, j, d});
                    }
                }
            }
        }
        int min_dist = dist[end.x][end.y][0];
        for(int d = 1;d<4;++d) {
            min_dist = Math.min(min_dist, dist[end.x][end.y][d]);
        }
        TreeSet<PairInt> cells = new TreeSet<>();
        for(int d = 0;d<4;++d) {
            if(dist[end.x][end.y][d] != min_dist) continue;
            LinkedList<int[]> q = new LinkedList<>();
            q.add(new int[]{end.x, end.y, d});
            while(!q.isEmpty()) {
                int[] cur = q.poll();
                cells.add(new PairInt(cur[0], cur[1]));
                for(int[] parent: parents[cur[0]][cur[1]][cur[2]]) {
                    q.add(parent);
                }
            }
        }
        System.out.println(cells.size());
    }
}
