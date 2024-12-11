package src.q7;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q7a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        long ans = 0;
        for (String l : lines) {
            String[] l2 = l.split(": ");
            long res = Long.parseLong(l2[0]);
            Integer[] a = Utils.splitMapInt(l2[1], " ");
            int n = a.length;
            boolean ok = false;
            for (long bm = 0; bm < (1L << (n - 1)); ++bm) {
                long cur = a[0];
                for (int i = 0; i < n - 1; ++i) {
                    if (((bm >> i) & 1) == 1) {
                        cur *= a[i + 1];
                    } else {
                        cur += a[i + 1];
                    }
                }
                if (cur == res) {
                    ok = true;
                    break;
                }
            }
            if (ok)
                ans += res;
        }
        System.out.println(ans);
    }
}
