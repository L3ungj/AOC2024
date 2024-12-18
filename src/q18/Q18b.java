package src.q18;

import java.util.LinkedList;

import src.utils.InputFileReader;
import src.utils.Utils;
import src.utils.PairInt;

public class Q18b {
    static final int n = 71, m = 71;
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        char[][] grid = new char[n][m];
        for(int i=0;i<lines.length;++i) {
            Integer[] xy = Utils.splitMapInt(lines[i], ",");
            grid[xy[0]][xy[1]] = '#';
            if(i < 1024) continue;
            LinkedList<PairInt> q = new LinkedList<>();
            int dist[][] = new int[n][m];
            q.add(new PairInt(0, 0));
            dist[0][0] = 1;
            while(!q.isEmpty()) {
                PairInt p = q.poll();
                for(PairInt dir: Utils.dirs) {
                    PairInt np = PairInt.add(p, dir);
                    if(Utils.inBounds(np, n, m) && grid[np.x][np.y] != '#' && dist[np.x][np.y] == 0) {
                        dist[np.x][np.y] = dist[p.x][p.y] + 1;
                        q.add(np);
                    }
                }
            }
            // System.out.println(dist[n-1][m-1] - 1);
            if(dist[n-1][m-1] == 0) {
                System.out.println(xy[0] + "," + xy[1]);
                break;
            }
        }
    }
}
