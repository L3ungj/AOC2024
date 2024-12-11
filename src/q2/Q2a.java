package src.q2;

import src.utils.InputFileReader;
import java.util.stream.Stream;

public class Q2a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int ans = 0;
        for (String line : lines) {
            int[] integers = Stream.of(line.split(" ")).mapToInt(w -> Integer.parseInt(w)).toArray();
            boolean inc = false;
            if (integers[0] < integers[1])
                inc = true;
            boolean ok = true;
            for (int i = 0; i < integers.length - 1; ++i) {
                int diff = integers[i+1] - integers[i];
                if (Math.abs(diff) < 1 || Math.abs(diff) > 3)
                    ok = false;
                if (inc && diff < 0)
                    ok = false;
                if (!inc && diff > 0)
                    ok = false;
            }
            if (ok)
                ans++;
        }
        System.out.println(ans);
    }
}
