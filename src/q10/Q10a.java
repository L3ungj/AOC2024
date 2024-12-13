package src.q10;

import java.util.ArrayList;
import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q10a {
    static int[][] findNines(char[][] grid) {
        ArrayList<int[]> ret = new ArrayList<>();
        for(int i=0;i<grid.length;++i) for(int j=0;j<grid[0].length;++j) {
            if(grid[i][j] == '9') ret.add(new int[]{i, j});
        }
        int[][] ret_ = new int[0][0];
        return ret.toArray(ret_);
    }

    static final int[] dirx = {1, -1, 0, 0};
    static final int[] diry = {0, 0, -1, 1};

    static int getScore(char[][] grid, int[] nine) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        LinkedList<int[]> q = new LinkedList<>();
        q.add(nine);
        vis[nine[0]][nine[1]] = true;
        int score = 0;
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0], y = xy[1];
            if(grid[x][y] == '0') {
                score++;
                continue;
            }
            for(int d = 0;d < 4; ++d) {
                int nx = x + dirx[d], ny = y + diry[d];
                if(!Utils.inBounds(nx, ny, n, m) || vis[nx][ny])
                    continue;
                if(grid[nx][ny] == grid[x][y] - 1) {
                    q.add(new int[]{nx, ny});
                    vis[nx][ny] = true;
                }
            }
        }
        return score;
    } 

    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int[][] nines = findNines(grid);
        int ans = 0;
        for(int[] nine:nines) {
            ans += getScore(grid, nine);
        }
        System.out.println(ans);
    }
}
