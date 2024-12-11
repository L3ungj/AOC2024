package src.q7;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q7b {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        long ans = 0;
        for (String l : lines) {
            String[] l2 = l.split(": ");
            long res = Long.parseLong(l2[0]);
            Integer[] a = Utils.splitMapInt(l2[1], " ");
            int n = a.length;
            boolean ok = false;
            for (long bm = 0; bm < Math.pow(3, n-1) + 0.5; ++bm) {
                long cur = a[0];
                long tbm = bm;
                for (int i = 0; i < n - 1; ++i) {
                    if (tbm % 3 == 0) {
                        cur *= a[i + 1];
                    } else if (tbm % 3 == 1) {
                        cur += a[i + 1];
                    } else {
                        String scur = String.valueOf(cur) + String.valueOf(a[i+1]);
                        cur = Long.parseLong(scur);
                    }
                    tbm /= 3;
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
