package src.q22;

import src.utils.InputFileReader;

public class Q22a {
    static Long next(Long n) {
        Long n1 = (n ^ (n * 64)) % 16777216;
        Long n2 = (n1 ^ (n1 / 32)) % 16777216;
        Long n3 = (n2 ^ (n2 * 2048)) % 16777216;
        return n3;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        Long ans = 0L;
        for (String s : lines) {
            Long n = Long.parseLong(s);
            for(int i = 0; i < 2000; i++) {
                n = next(n);
            }
            ans += n;
        }
        System.out.println(ans);
    }
}
