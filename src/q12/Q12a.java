package src.q12;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;



public class Q12a {
    public static void main(String[] args) {
        char[][] grid = InputFileReader.readGrid();
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        long ans = 0;
        for(int i=0;i<n;++i) for(int j=0;j<m;++j) {
            if(vis[i][j]) continue;
            LinkedList<int[]> q = new LinkedList<>();
            vis[i][j] = true;
            q.add(new int[]{i, j});
            int totalAdjs = 0;
            int area = 0;
            while(!q.isEmpty()) {
                int[] xy = q.poll();
                int x = xy[0], y = xy[1];
                int adjs = 0;
                for(int d = 0; d < 4; ++d) {
                    int nx = x + Utils.dirx[d];
                    int ny = y + Utils.diry[d];
                    if(!Utils.inBounds(nx, ny, n, m)) continue;
                    if(grid[nx][ny] != grid[x][y]) continue;
                    ++adjs;
                    if(vis[nx][ny]) continue;
                    vis[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
                totalAdjs += adjs;
                ++area;
            }
            int perimeter = area * 4 - totalAdjs;
            ans += perimeter * area;
        }
        System.out.println(ans);
    }
}
