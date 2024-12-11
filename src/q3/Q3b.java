package src.q3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import src.utils.InputFileReader;

public class Q3b {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        String raw = String.join("\n", lines);
        Matcher matcher = Pattern.compile("(mul[(][1-9][0-9]?[0-9]?,[1-9][0-9]?[0-9]?[)])|(do[(][)])|(don't[(][)])").matcher(raw);
        long ans = 0;
        boolean activated = true;
        while(matcher.find()) {
            String s = matcher.group();
            if(s.equals("do()")) activated = true;
            else if(s.equals("don't()")) activated = false;
            else if(activated){
                s = s.substring(4, s.length()-1);
                int[] ints = Stream.of(s.split(",")).mapToInt(w -> Integer.parseInt(w)).toArray();
                ans += ints[0] * ints[1];
            }
        }
        System.out.println(ans);
    }
}
