package src.q11;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q11a {
    static long stones(long n, int times) {
        if(times == 0) return 1;
        if(n == 0) return stones(1, times - 1);
        String s = Long.toString(n);
        if(s.length() % 2 == 0) {
            Long a = Long.parseLong(s.substring(0, s.length() / 2));
            Long b = Long.parseLong(s.substring(s.length() / 2));
            return stones(a, times - 1) + stones(b, times - 1);
        }
        return stones(n * 2024, times - 1);
    }
    public static void main(String[] args) {
        String line = InputFileReader.readLine();
        Integer[] a = Utils.splitMapInt(line, " ");
        long ans = 0;
        for (Integer n : a) {
            ans += stones(n, 25);
        }
        System.out.println(ans);
    }
}
