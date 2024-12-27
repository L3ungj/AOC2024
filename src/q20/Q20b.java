package src.q20;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.PairInt;
import src.utils.Utils;

public class Q20b {
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
        for(int i1=0;i1<n;++i1) {
            for(int j1=0;j1<m;++j1) {
                for(int i2=0;i2<n;++i2) {
                    for(int j2=0;j2<m; ++j2) {
                        if(grid[i1][j1] == '#' || grid[i2][j2] == '#' || Math.abs(i2-i1) + Math.abs(j2-j1) > 20) continue;
                        int timeSaved = Math.abs(dist[i1][j1] - dist[i2][j2]) - (Math.abs(i2-i1) + Math.abs(j2-j1));
                        if(timeSaved >= 100) {
                            // System.out.println(timeSaved);
                            ++ans;
                        }
                    }
                }
            }
        }
        System.out.println(ans / 2);
    }
}
