package src.q10;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q10b {
    static int getRating(char[][] grid, int[] nine) {
        int n = grid.length, m = grid[0].length;
        long[][] paths = new long[n][m];
        LinkedList<int[]> q = new LinkedList<>();
        q.add(nine);
        paths[nine[0]][nine[1]] = 1;
        int rating = 0;
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0], y = xy[1];
            for(int d = 0;d < 4; ++d) {
                int nx = x + Q10a.dirx[d], ny = y + Q10a.diry[d];
                if(!Utils.inBounds(nx, ny, n, m))
                    continue;
                if(grid[nx][ny] == grid[x][y] - 1) {
                    if(grid[nx][ny] == '0') {
                        rating += paths[x][y];
                        continue;
                    }
                    if(paths[nx][ny] == 0) {
                        q.add(new int[]{nx, ny});
                    }
                    paths[nx][ny] += paths[x][y];
                }
            }
        }
        return rating;
    }

    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int[][] nines = Q10a.findNines(grid);
        long ans = 0;
        for(int[] nine:nines) {
            ans += getRating(grid, nine);
        }
        System.out.println(ans);
    }
}
