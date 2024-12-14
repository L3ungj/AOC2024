package src.q14;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q14b {
    static final int n = 101, m = 103;

    static int[][] updatePositions(int[][] positions, int[][] velocities) {
        for(int i = 0; i < positions.length; i++) {
            positions[i][0] += velocities[i][0];
            positions[i][1] += velocities[i][1];
            positions[i][0] %= n;
            positions[i][1] %= m;
            if(positions[i][0] < 0) positions[i][0] += n;
            if(positions[i][1] < 0) positions[i][1] += m;
        }
        return positions;
    }

    static boolean clumped(int[][] grid) {
        boolean vis[][] = new boolean[m][n];
        int clumps = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != 0 && !vis[i][j]) {
                    LinkedList<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    vis[i][j] = true;
                    ++clumps;
                    while(!q.isEmpty()) {
                        int[] p = q.poll();
                        int x = p[0], y = p[1];
                        for(int d = 0; d < 4; ++d) {
                            int nx = x + Utils.dirx[d];
                            int ny = y + Utils.diry[d];
                            if(!Utils.inBounds(nx, ny, m, n)) continue;
                            if(vis[nx][ny] || grid[nx][ny] != 1) continue;
                            vis[nx][ny] = true;
                            q.add(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        // System.out.println(clumps);
        final int cutoff = 300;
        return clumps <= cutoff;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int[][] positions = new int[lines.length][2];
        int[][] velocities = new int[lines.length][2];
        int idx = 0;
        for(String line: lines) {
            String[] ss = line.split("[=, ]");
            positions[idx][0] = Integer.parseInt(ss[1]);
            positions[idx][1] = Integer.parseInt(ss[2]);
            velocities[idx][0] = Integer.parseInt(ss[4]);
            velocities[idx][1] = Integer.parseInt(ss[5]);
            idx++;
        }
        idx = 0;
        while(true) {
            ++idx;
            // if(idx % 100 == 0) System.out.println(idx);
            positions = updatePositions(positions, velocities);
            int[][] grid = new int[m][n];
            for(int[] pos: positions) {
                grid[pos[1]][pos[0]] = 1;
            }
            if(!clumped(grid)) continue;
            System.out.println(idx);
            // for(int i=0; i<m;++i) {
            //     for(int j=0; j<n;++j) {
            //         System.out.print(grid[i][j] == 1 ? "##" : "  ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            break;
        }
    }
}
