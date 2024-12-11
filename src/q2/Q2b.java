package src.q2;

import src.utils.InputFileReader;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;

public class Q2b {
    public static boolean isSafe(int[] integers) {
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
        return ok;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int ans = 0;
        for (String line : lines) {
            int[] integers = Stream.of(line.split(" ")).mapToInt(w -> Integer.parseInt(w)).toArray();
            boolean ok = false;
            if(isSafe(integers)) ok = true;
            for(int i = 0; i < integers.length; ++i) {
                int[] new_integers = ArrayUtils.remove(integers, i);
                if(isSafe(new_integers)) ok = true;
            }
            if(ok) ++ans;
        }
        System.out.println(ans);
    }
}
