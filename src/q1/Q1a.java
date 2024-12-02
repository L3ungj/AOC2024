package src.q1;

import java.util.Arrays;

import src.utils.InputFileReader;

public class Q1a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int n = lines.length;
        int[] as = new int[n], bs = new int[n];
        for (int i = 0; i < n; ++i) {
            String[] ss = lines[i].split("   ");
            as[i] = Integer.parseInt(ss[0]);
            bs[i] = Integer.parseInt(ss[1]);
        }
        Arrays.sort(as);
        Arrays.sort(bs);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.abs(as[i] - bs[i]);
        }
        System.out.println(ans);
    }
}
