package src.q21;

import java.util.Map;

import src.utils.InputFileReader;

public class Q21a {
    static String press(String code) {
        int x = 2, y = 1;
        Map<Character, Integer> px = Map.of('A', 2, '>', 2, '^', 1, 'v', 1, '<', 0);
        Map<Character, Integer> py = Map.of('A', 1, '>', 0, '^', 1, 'v', 0, '<', 0);
        StringBuilder sb = new StringBuilder();
        for(char c: code.toCharArray()) {
            while(x < px.get(c)) {
                ++x;
                sb.append('>');
            }
            while(y > py.get(c)) {
                --y;
                sb.append('v');
            }
            while(x > px.get(c)) {
                --x;
                sb.append('<');
            }
            while(y < py.get(c)) {
                ++y;
                sb.append('^');
            }
            sb.append('A');
        }
        return sb.toString();
    }   

    static int solve(String code) {
        int x = 2, y = 0;
        int[] px = {1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 2};
        int[] py = {0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0};
        StringBuilder sb = new StringBuilder();
        for(char c: code.toCharArray()) {
            if (c == 'A') c = '0' + 10;
            while(x < px[c - '0']) {
                ++x;
                sb.append('>');
            }
            while(y < py[c - '0']) {
                ++y;
                sb.append('^');
            }
            while(x > px[c - '0']) {
                --x;
                sb.append('<');
            }
            while(y > py[c - '0']) {
                --y;
                sb.append('v');
            }
            sb.append('A');
        }
        System.out.println(sb.toString());
        String s2 = press(sb.toString());
        System.out.println(s2);
        String s3 = press(s2);
        System.out.println(s3);
        System.out.println(s3.length());
        return s3.length();
    }

    public static void main(String[] args) {
        int ans = 0;
        String[] lines = InputFileReader.readAllLines();
        for(String code : lines) {
            int num = Integer.parseInt(code.substring(0, code.length() - 1));
            ans += solve(code) * num;
        }
        System.out.println(ans);
    }
}
