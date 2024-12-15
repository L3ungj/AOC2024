package src.q15;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q15b {
    char[][] grid;
    int n, m;

    int[] findStart() {
        for(int i=0;i<n;++i) for(int j=0;j<m;++j) {
            if(grid[i][j] == '@') return new int[]{i, j};
        }
        return new int[]{-1, -1};
    }

    boolean canMoveBox(int[] pos, int d) {
        int x = pos[0], y = pos[1];
        int nx = x + Utils.dirx[d], ny = y + Utils.diry[d];
        if(d <= 1) {
            boolean ok = true;
            if(grid[nx][ny] == ']') ok &= canMoveBox(new int[]{nx, ny-1}, d);
            if(grid[nx][ny+1] == '[') ok &= canMoveBox(new int[]{nx, ny+1}, d);
            if(grid[nx][ny+1] == ']') ok &= canMoveBox(new int[]{nx, ny}, d);
            if(grid[nx][ny] == '#' || grid[nx][ny+1] == '#') ok = false;
            return ok;
        }
        if(d == 3) ++ny;
        boolean ok = true;
        if(grid[nx][ny] == ']') ok &= canMoveBox(new int[]{nx, ny-1}, d);
        if(grid[nx][ny] == '[') ok &= canMoveBox(new int[]{nx, ny}, d);
        if(grid[nx][ny] == '#') ok = false;
        return ok;
    }

    void moveBox(int[] pos, int d) {
        int x = pos[0], y = pos[1];
        grid[x][y] = '.';
        grid[x][y+1] = '.';
        int nx = x + Utils.dirx[d], ny = y + Utils.diry[d];
        if(d <= 1) {
            if(grid[nx][ny] == ']') moveBox(new int[]{nx, ny-1}, d);
            if(grid[nx][ny+1] == '[') moveBox(new int[]{nx, ny+1}, d);
            if(grid[nx][ny+1] == ']') moveBox(new int[]{nx, ny}, d);
            grid[nx][ny] = '[';
            grid[nx][ny+1] = ']';
            return;
        }
        if(d == 3) ++ny;
        if(grid[nx][ny] == ']') moveBox(new int[]{nx, ny-1}, d);
        if(grid[nx][ny] == '[') moveBox(new int[]{nx, ny}, d);
        if(d == 3) --ny;
        grid[nx][ny] = '[';
        grid[nx][ny+1] = ']';
    }

    void printGrid(int[] pos) {
        for(int i=0;i<n;++i) {
            for(int j=0;j<m;++j) {
                if(i == pos[0] && j == pos[1]) {
                    System.out.print('@');
                    continue;
                }
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    void main_() {
        String[] lines = InputFileReader.readAllLines();
        int t = 0;
        for (int i = 0; i < lines.length; ++i)
            if (lines[i].isEmpty())
                t = i;
        grid = Stream.of(Arrays.copyOfRange(lines, 0, t))
                .map(s -> {
                    char[] arr = new char[s.length() * 2];
                    for (int i = 0; i < s.length(); ++i) {
                        if (s.charAt(i) == '#') {
                            arr[i * 2] = '#';
                            arr[i * 2 + 1] = '#';
                            continue;
                        }
                        if (s.charAt(i) == 'O') {
                            arr[i * 2] = '[';
                            arr[i * 2 + 1] = ']';
                            continue;
                        }
                        arr[i * 2] = s.charAt(i);
                        arr[i * 2 + 1] = '.';
                    }
                    return arr;
                }).toArray(char[][]::new);
        n = grid.length;
        m = grid[0].length;
        String ops = String.join("", Arrays.copyOfRange(lines, t + 1, lines.length));
        Map<Character, Integer> dirMap = Map.of('v', 0, '^', 1, '<', 2, '>', 3);
        int[] pos = findStart();
        int x = pos[0], y = pos[1];
        grid[pos[0]][pos[1]] = '.';
        for(char c : ops.toCharArray()) {
            int d = dirMap.get(c);
            int nx = x + Utils.dirx[d];
            int ny = y + Utils.diry[d];
            if(grid[nx][ny] == '#') continue;
            if(grid[nx][ny] == ']') {
                if(!canMoveBox(new int[]{nx, ny-1}, d)) continue;
                moveBox(new int[]{nx, ny-1}, d);
            }
            else if(grid[nx][ny] == '[') {
                if(!canMoveBox(new int[]{nx, ny}, d)) continue;
                moveBox(new int[]{nx, ny}, d);
            }
            x = nx;
            y = ny;
            // printGrid(new int[]{x, y});
        }
        int ans = 0;
        for(int i=0;i<n;++i) {
            for(int j=0;j<m;++j) {
                if(grid[i][j] == '[') 
                    ans += i*100 + j;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Q15b q15b = new Q15b();
        q15b.main_();
    }
}
