package src.q11;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q11b {
    long[][] memo = new long[2025][76];

    long stones(long n, int times) {
        if(n <= 2024 && memo[(int)n][times] != 0) {
            return memo[(int)n][times];
        }
        if(times == 0) return 1;
        long res;
        String s = Long.toString(n);
        if(n == 0) res = stones(1, times - 1);
        else if(s.length() % 2 == 0) {
            Long a = Long.parseLong(s.substring(0, s.length() / 2));
            Long b = Long.parseLong(s.substring(s.length() / 2));
            res = stones(a, times - 1) + stones(b, times - 1);
        } else
            res = stones(n * 2024, times - 1);
        if(n <= 2024) memo[(int)n][times] = res;
        return res;
    }
    public static void main(String[] args) {
        String line = InputFileReader.readLine();
        Integer[] a = Utils.splitMapInt(line, " ");
        long ans = 0;
        Q11b q11b = new Q11b();
        for (Integer n : a) {
            ans += q11b.stones(n, 75);
        }
        System.out.println(ans);
    }
}
