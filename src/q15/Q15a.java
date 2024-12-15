package src.q15;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q15a {
    char[][] grid;
    int n, m;

    char getCell(int[] pos) {
        return grid[pos[0]][pos[1]];
    }

    int[] findStart() {
        for(int i=0;i<n;++i) for(int j=0;j<m;++j) {
            if(grid[i][j] == '@') return new int[]{i, j};
        }
        return new int[]{-1, -1};
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

    int[] nextNonBox(int x, int y, int d) {
        for(int k = 1;;++k) {
            int nx = x + k*Utils.dirx[d];
            int ny = y + k*Utils.diry[d];
            if(!Utils.inBounds(nx, ny, n, m) || grid[nx][ny] != 'O') {
                return new int[]{nx, ny};
            }
        }
    }

    void main_() {
        String[] lines = InputFileReader.readAllLines();
        int t = 0;
        for(int i=0;i<lines.length;++i) if(lines[i].isEmpty()) t = i;
        grid = Stream.of(Arrays.copyOfRange(lines, 0, t)).map(String::toCharArray).toArray(char[][]::new);
        n = grid.length; m = grid[0].length;
        String ops = String.join("", Arrays.copyOfRange(lines, t+1, lines.length));
        Map<Character, Integer> dirMap = Map.of('v', 0, '^', 1, '<', 2, '>', 3);
        int[] pos = findStart();
        grid[pos[0]][pos[1]] = '.';
        for(char c : ops.toCharArray()) {
            int[] nonBoxPos = nextNonBox(pos[0], pos[1], dirMap.get(c));
            int[] npos = new int[]{pos[0] + Utils.dirx[dirMap.get(c)], pos[1] + Utils.diry[dirMap.get(c)]};
            if(!Utils.inBounds(nonBoxPos[0], nonBoxPos[1], n, m) || getCell(nonBoxPos) == '#') {
                continue;
            }
            grid[nonBoxPos[0]][nonBoxPos[1]] = 'O'; // box move
            grid[npos[0]][npos[1]] = '.'; // move
            pos = npos;
            // printGrid(pos);
        }
        int ans = 0;
        for(int i=0;i<n;++i) {
            for(int j=0;j<m;++j) {
                if(grid[i][j] == 'O')
                    ans += 100*i + j;
            }
        }
        System.out.println(ans);
    }
    public static void main(String[] args) {
        Q15a q15a = new Q15a();
        q15a.main_();
    }
}
