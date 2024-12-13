package src.q12;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q12b {
    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        long ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                if (vis[i][j])
                    continue;
                LinkedList<int[]> q = new LinkedList<>();
                vis[i][j] = true;
                q.add(new int[] { i, j });
                int area = 0;
                boolean[][][] isSide = new boolean[4][n][m];
                while (!q.isEmpty()) {
                    int[] xy = q.poll();
                    int x = xy[0], y = xy[1];
                    for (int d = 0; d < 4; ++d) {
                        int nx = x + Utils.dirx[d];
                        int ny = y + Utils.diry[d];
                        if (!Utils.inBounds(nx, ny, n, m) || grid[nx][ny] != grid[x][y]) {
                            isSide[d][x][y] = true;
                            continue;
                        }
                        if (vis[nx][ny])
                            continue;
                        vis[nx][ny] = true;
                        q.add(new int[] { nx, ny });
                    }
                    ++area;
                }
                int sides = 0;
                for (int jj = 0; jj < m; ++jj) {
                    for (int ii = 0; ii < n; ++ii) {
                        if (isSide[2][ii][jj] && (ii == 0 || !isSide[2][ii - 1][jj]))
                            ++sides;
                        if (isSide[3][ii][jj] && (ii == 0 || !isSide[3][ii - 1][jj]))
                            ++sides;
                    }
                }
                for (int ii = 0; ii < n; ++ii) {
                    for (int jj = 0; jj < m; ++jj) {
                        if (isSide[0][ii][jj] && (jj == 0 || !isSide[0][ii][jj-1]))
                            ++sides;
                        if (isSide[1][ii][jj] && (jj == 0 || !isSide[1][ii][jj-1]))
                            ++sides;
                    }
                }
                // System.out.println(String.format("%d %d", sides, area));
                ans += sides * area;
            }
        System.out.println(ans);
    }
}
