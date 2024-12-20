package src.q19;

import src.utils.InputFileReader;

public class Q19b {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        String[] words = lines[0].split(", ");
        long ans = 0;
        for(int i = 2; i < lines.length; ++i) {
            String s = lines[i];
            int n = s.length();
            long[] ways = new long[n+1];
            ways[0] = 1;
            for(int j = 0; j < n; ++j) {
                for(String w : words) {
                    int m = w.length();
                    if(j + m <= n && s.substring(j, j+m).equals(w)) {
                        ways[j+m] += ways[j];
                    }
                }
            }
            ans += ways[n];
        }
        System.out.println(ans);
    }
}
