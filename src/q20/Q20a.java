package src.q20;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.PairInt;
import src.utils.Utils;

public class Q20a {
    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int n = grid.length, m = grid[0].length;
        int[][] dist = new int[n][m];
        int sx = -1, sy = -1;
        for(int i=0;i<n;++i) {
            for(int j=0;j<m;++j) {
                if(grid[i][j] == 'S') {
                    sx = i;
                    sy = j;
                }
            }
        }
        LinkedList<PairInt> q = new LinkedList<>();
        q.add(new PairInt(sx, sy));
        dist[sx][sy] = 1;
        while(!q.isEmpty()) {
            PairInt p = q.poll();
            for(int d=0;d<4;++d) {
                PairInt np = PairInt.add(p, Utils.dirs[d]);
                if(Utils.inBounds(np, n, m) && grid[np.x][np.y] != '#' && dist[np.x][np.y] == 0) {
                    dist[np.x][np.y] = dist[p.x][p.y] + 1;
                    q.add(np);
                }
            }
        }
        int ans = 0;
        for(int i=0;i<n;++i) {
            for(int j=0;j<m;++j) {
                if(grid[i][j] != '#') continue;
                for(int d1 = 0; d1 < 4; ++d1) {
                    for(int d2 = d1 + 1; d2 < 4; ++d2) {
                        PairInt p1 = PairInt.add(new PairInt(i, j), Utils.dirs[d1]);
                        PairInt p2 = PairInt.add(new PairInt(i, j), Utils.dirs[d2]);
                        if(!p1.inBounds(n, m) || !p2.inBounds(n, m)) continue;
                        if(dist[p1.x][p1.y] == 0 || dist[p2.x][p2.y] == 0) continue;
                        int timeSaved = Math.abs(dist[p2.x][p2.y] - dist[p1.x][p1.y]) - 2;
                        if(timeSaved >= 100) {
                            ++ans;
                            // System.out.println(timeSaved);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
