package src.q13;

import src.utils.InputFileReader;

public class Q13a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int ans = 0;
        for (int i = 0; i < (lines.length + 1) / 4; ++i) {
            int[] dx = new int[2];
            int[] dy = new int[2];
            for (int j = 0; j < 2; ++j) {
                String[] s1 = lines[4 * i + j].split("[+]");
                dx[j] = Integer.parseInt(s1[1].substring(0, s1[1].indexOf(',')));
                dy[j] = Integer.parseInt(s1[2]);
            }
            int px, py;
            {
                String[] s1 = lines[4 * i + 2].split("=");
                px = Integer.parseInt(s1[1].substring(0, s1[1].indexOf(',')));
                py = Integer.parseInt(s1[2]);
            }
            int minCost = 1000000000;
            for (int a = 0; a < 101; ++a) {
                for (int b = 0; b < 101; ++b) {
                    int cost = a * 3 + b;
                    if (a * dx[0] + b * dx[1] == px && a * dy[0] + b * dy[1] == py) {
                        minCost = Math.min(minCost, cost);
                    }
                }
            }
            if (minCost != 1000000000)
                ans += minCost;
        }
        System.out.println(ans);
    }
}
