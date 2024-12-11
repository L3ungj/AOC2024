package src.q4;

import src.utils.InputFileReader;

public class Q4a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int n = lines.length;
        int m = lines[0].length();
        final String XMAS = "XMAS";
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = 0; j < m; ++j) {
                for (int di = -1; di <= 1; ++di)
                    for (int dj = -1; dj <= 1; ++dj) {
                        if (di == 0 && dj == 0)
                            continue;
                        int k = 0;
                        for (; k < 4; ++k) {
                            int ni = i + di * k, nj = j + dj * k;
                            if (ni < 0 || ni >= n || nj < 0 || nj >= m || lines[ni].charAt(nj) != XMAS.charAt(k))
                                break;
                        }
                        if(k == 4) ++ans;
                    }
            }
        System.out.println(ans);
    }
}
