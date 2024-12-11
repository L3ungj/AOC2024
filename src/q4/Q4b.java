package src.q4;

import src.utils.InputFileReader;

public class Q4b {
    public static boolean chk(char a, char b) {
        return (a == 'M' && b == 'S') || (a == 'S' && b == 'M');
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int n = lines.length;
        int m = lines[0].length();
        int ans = 0;
        for (int i = 1; i < n - 1; ++i)
            for (int j = 1; j < m - 1; ++j) {
                if (lines[i].charAt(j) == 'A' &&
                        chk(lines[i - 1].charAt(j - 1), lines[i + 1].charAt(j + 1)) &&
                        chk(lines[i - 1].charAt(j + 1), lines[i + 1].charAt(j - 1))) {
                    ++ans;
                }
            }
        System.out.println(ans);
    }
}
