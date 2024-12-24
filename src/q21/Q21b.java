package src.q21;

import java.util.HashMap;
import java.util.Map;

import src.utils.InputFileReader;

public class Q21b {
    HashMap<String, Long> memo = new HashMap<>();
    long press(int x, int y, char c, int t) {
        String s = "" + x + y + c + t;
        if (memo.containsKey(s))
            return memo.get(s);
        if (t == 0)
            return 1;
        Map<Character, Integer> px = Map.of('A', 2, '>', 2, '^', 1, 'v', 1, '<', 0);
        Map<Character, Integer> py = Map.of('A', 1, '>', 0, '^', 1, 'v', 0, '<', 0);
        int dx = px.get(c) - x, dy = py.get(c) - y;
        long res = (1L << 63) - 1;
        if (!(c == '<' && y == 1)) { // avoid out of bounds
            long cur = 0;
            int nx = px.get('A'), ny = py.get('A');
            if (dx > 0) {
                cur += Math.abs(dx) - 1 + press(nx, ny, '>', t - 1);
                nx = px.get('>');
                ny = py.get('>');
            } else if (dx < 0) {
                cur += Math.abs(dx) - 1 + press(nx, ny, '<', t - 1);
                nx = px.get('<');
                ny = py.get('<');
            }
            if (dy > 0) {
                cur += Math.abs(dy) - 1 + press(nx, ny, '^', t - 1);
                nx = px.get('^');
                ny = py.get('^');
            } else if (dy < 0) {
                cur += Math.abs(dy) - 1 + press(nx, ny, 'v', t - 1);
                nx = px.get('v');
                ny = py.get('v');
            }
            cur += press(nx, ny, 'A', t - 1);
            res = Math.min(res, cur);
        }
        if (!(x == 0 && dy > 0)) {
            long cur = 0;
            int nx = px.get('A'), ny = py.get('A');
            if (dy > 0) {
                cur += Math.abs(dy) - 1 + press(nx, ny, '^', t - 1);
                nx = px.get('^');
                ny = py.get('^');
            } else if (dy < 0) {
                cur += Math.abs(dy) - 1 + press(nx, ny, 'v', t - 1);
                nx = px.get('v');
                ny = py.get('v');
            }
            if (dx > 0) {
                cur += Math.abs(dx) - 1 + press(nx, ny, '>', t - 1);
                nx = px.get('>');
                ny = py.get('>');
            } else if (dx < 0) {
                cur += Math.abs(dx) - 1 + press(nx, ny, '<', t - 1);
                nx = px.get('<');
                ny = py.get('<');
            }
            cur += press(nx, ny, 'A', t - 1);
            res = Math.min(res, cur);
        }
        memo.put(s, res);
        return res;
    }

    long solve(String code, int t) {
        int x = 2, y = 0;
        int[] px = { 1, 0, 1, 2, 0, 1, 2, 0, 1, 2, 2 };
        int[] py = { 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 0 };
        Map<Character, Integer> mpx = Map.of('A', 2, '>', 2, '^', 1, 'v', 1, '<', 0);
        Map<Character, Integer> mpy = Map.of('A', 1, '>', 0, '^', 1, 'v', 0, '<', 0);
        long tot = 0;
        for (char c : code.toCharArray()) {
            if (c == 'A')
                c = '0' + 10;
            int dx = px[c - '0'] - x, dy = py[c - '0'] - y;
            long res = (1L << 63) - 1;
            if (!(x == 0 && py[c - '0'] == 0)) {
                int nx = mpx.get('A'), ny = mpy.get('A');
                long cur = 0;
                if (dy > 0) {
                    cur += Math.abs(dy) - 1 + press(nx, ny, '^', t - 1);
                    nx = mpx.get('^');
                    ny = mpy.get('^');
                } else if (dy < 0) {
                    cur += Math.abs(dy) - 1 + press(nx, ny, 'v', t - 1);
                    nx = mpx.get('v');
                    ny = mpy.get('v');
                }
                if (dx > 0) {
                    cur += Math.abs(dx) - 1 + press(nx, ny, '>', t - 1);
                    nx = mpx.get('>');
                    ny = mpy.get('>');
                } else if (dx < 0) {
                    cur += Math.abs(dx) - 1 + press(nx, ny, '<', t - 1);
                    nx = mpx.get('<');
                    ny = mpy.get('<');
                }
                cur += press(nx, ny, 'A', t - 1);
                res = Math.min(res, cur);
            }
            if (!(y == 0 && px[c - '0'] == 0)) {
                int nx = mpx.get('A'), ny = mpy.get('A');
                long cur = 0;
                if (dx > 0) {
                    cur += Math.abs(dx) - 1 + press(nx, ny, '>', t - 1);
                    nx = mpx.get('>');
                    ny = mpy.get('>');
                } else if (dx < 0) {
                    cur += Math.abs(dx) - 1 + press(nx, ny, '<', t - 1);
                    nx = mpx.get('<');
                    ny = mpy.get('<');
                }
                if (dy > 0) {
                    cur += Math.abs(dy) - 1 + press(nx, ny, '^', t - 1);
                    nx = mpx.get('^');
                    ny = mpy.get('^');
                } else if (dy < 0) {
                    cur += Math.abs(dy) - 1 + press(nx, ny, 'v', t - 1);
                    nx = mpx.get('v');
                    ny = mpy.get('v');
                }
                cur += press(nx, ny, 'A', t - 1);
                res = Math.min(res, cur);
            }
            tot += res;
            x = px[c - '0'];
            y = py[c - '0'];
        }
        // System.out.println(tot);
        return tot;
    }

    public static void main(String[] args) {
        Q21b q = new Q21b();
        long ans = 0;
        String[] lines = InputFileReader.readAllLines();
        for (String code : lines) {
            int num = Integer.parseInt(code.substring(0, code.length() - 1));
            ans += q.solve(code, 26) * num;
            // System.out.println(ans);
        }
        System.out.println(ans);
    }
}
