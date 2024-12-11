package src.q3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import src.utils.InputFileReader;

public class Q3a {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        String raw = String.join("\n", lines);
        Matcher matcher = Pattern.compile("mul[(][1-9][0-9]?[0-9]?,[1-9][0-9]?[0-9]?[)]").matcher(raw);
        long ans = 0;
        while(matcher.find()) {
            String s = matcher.group();
            s = s.substring(4, s.length()-1);
            int[] ints = Stream.of(s.split(",")).mapToInt(w -> Integer.parseInt(w)).toArray();
            ans += ints[0] * ints[1];
        }
        System.out.println(ans);
    }
}
