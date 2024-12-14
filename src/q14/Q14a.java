package src.q14;

import src.utils.InputFileReader;

public class Q14a {

    static final int n = 101, m = 103, t = 100; 
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int[] quadrantCount = new int[4];
        for(String line: lines) {
            String[] ss = line.split("[=, ]");
            int px = Integer.parseInt(ss[1]);
            int py = Integer.parseInt(ss[2]);
            int vx = Integer.parseInt(ss[4]);
            int vy = Integer.parseInt(ss[5]);
            px += vx * t; py += vy * t;
            px %= n; py %= m;
            if(px < 0) px += n;
            if(py < 0) py += m;
            int q = (px < n/2 ? 0 : px > n/2 ? 1 : -100) + (py < m/2 ? 0 : py > m/2 ? 2 : -100);
            if(q < 0) continue;
            quadrantCount[q]++;
        }
        System.out.println(quadrantCount[0] * quadrantCount[1] * quadrantCount[2] * quadrantCount[3]);
    }
}
