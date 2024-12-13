package src.q13;

import src.utils.InputFileReader;

public class Q13b {
    public static long det(long a, long b, long c, long d) {
        return a * d - b * c;
    }

    public static long[] solveSimul(long a1, long b1, long c1, long a2, long b2, long c2) {
        long d = det(a1, b1, a2, b2);
        long dx = det(c1, b1, c2, b2);
        long dy = det(a1, c1, a2, c2);
        if(d == 0) return null;
        if(dx % d != 0 || dy % d != 0) return null;
        return new long[] { dx / d, dy / d };
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        long ans = 0;
        for (int i = 0; i < (lines.length + 1) / 4; ++i) {
            int[] dx = new int[2];
            int[] dy = new int[2];
            for (int j = 0; j < 2; ++j) {
                String[] s1 = lines[4 * i + j].split("[+]");
                dx[j] = Integer.parseInt(s1[1].substring(0, s1[1].indexOf(',')));
                dy[j] = Integer.parseInt(s1[2]);
            }
            long px = 10000000000000L, py = 10000000000000L;
            {
                String[] s1 = lines[4 * i + 2].split("=");
                px += Integer.parseInt(s1[1].substring(0, s1[1].indexOf(',')));
                py += Integer.parseInt(s1[2]);
            }
            long[] res = solveSimul(dx[0], dx[1], px, dy[0], dy[1], py);
            if(res == null) continue;
            long a = res[0], b = res[1];
            if(a < 0 || b < 0) continue;
            ans += a * 3 + b;
        }
        System.out.println(ans);
    }
}
