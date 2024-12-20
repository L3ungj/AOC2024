package src.q19;

import src.utils.InputFileReader;

public class Q19a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        String[] words = lines[0].split(", ");
        int ans = 0;
        for(int i = 2; i < lines.length; ++i) {
            String s = lines[i];
            int n = s.length();
            boolean[] ok = new boolean[n+1];
            ok[0] = true;
            for(int j = 0; j < n; ++j) {
                for(String w : words) {
                    int m = w.length();
                    if(j + m <= n && s.substring(j, j+m).equals(w) && ok[j]) {
                        ok[j+m] = true;
                    }
                }
            }
            if(ok[n]) {
                ++ans;
            }
        }
        System.out.println(ans);
    }
}
