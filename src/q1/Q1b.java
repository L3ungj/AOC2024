package src.q1;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import src.utils.InputFileReader;

public class Q1b {

    public static long defZero(Long a) {
        if(a == null) return 0;
        return a;
    } 
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        int n = lines.length;
        int[] as = new int[n], bs = new int[n];
        for (int i = 0; i < n; ++i) {
            String[] ss = lines[i].split("   ");
            as[i] = Integer.parseInt(ss[0]);
            bs[i] = Integer.parseInt(ss[1]);
        }
        Map<Integer, Long> occB = Arrays.stream(bs).boxed().collect(
                Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += as[i] * defZero(occB.get(as[i]));
        }
        System.out.println(ans);
    }
}
