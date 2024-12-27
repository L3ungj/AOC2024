package src.q24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import src.utils.InputFileReader;

public class Q24a {
    HashMap<String, Integer> inDeg = new HashMap<>();
    HashMap<String, ArrayList<String>> out = new HashMap<>();
    HashMap<String, Boolean> val = new HashMap<>();
    HashMap<String, ArrayList<String>> op = new HashMap<>();

    void main_() {
        String[] lines = InputFileReader.readAllLines();
        LinkedList<String> q = new LinkedList<>();
        for (String line : lines) {
            if (line.isEmpty())
                continue;
            String[] words = line.split(" ");
            if (words.length == 2) {
                String u = words[0].substring(0, words[0].length() - 1);
                val.put(u, words[1].equals("1"));
                inDeg.put(u, 0);
                continue;
            }
            String u1 = words[0];
            String u2 = words[2];
            String v = words[4];
            if (!out.containsKey(u1)) {
                out.put(u1, new ArrayList<>());
            }
            out.get(u1).add(v);
            if (!out.containsKey(u2)) {
                out.put(u2, new ArrayList<>());
            }
            out.get(u2).add(v);
            if (!inDeg.containsKey(v)) {
                inDeg.put(v, 0);
            }
            if (!val.containsKey(u1))
                inDeg.put(v, inDeg.get(v) + 1);
            if (!val.containsKey(u2))
                inDeg.put(v, inDeg.get(v) + 1);
            if (inDeg.get(v) == 0) {
                q.add(v);
            }
            op.put(v, new ArrayList<>());
            op.get(v).add(u1);
            op.get(v).add(words[1]);
            op.get(v).add(u2);
        }
        while (!q.isEmpty()) {
            String u = q.poll();
            boolean b1 = val.get(op.get(u).get(0));
            boolean b2 = val.get(op.get(u).get(2));
            switch (op.get(u).get(1)) {
                case "AND":
                    val.put(u, b1 && b2);
                    break;
                case "OR":
                    val.put(u, b1 || b2);
                    break;
                case "XOR":
                    val.put(u, b1 ^ b2);
                    break;
            }
            if (!out.containsKey(u))
                continue;
            for (String v : out.get(u)) {
                inDeg.put(v, inDeg.get(v) - 1);
                if (inDeg.get(v) == 0) {
                    q.add(v);
                }
            }
        }
        long ans = 0;
        for (int i = 0;; ++i) {
            String s = String.format("z%02d", i);
            if (!val.containsKey(s))
                break;
            if (val.get(s)) {
                ans |= 1L << i;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Q24a q24a = new Q24a();
        q24a.main_();
    }
}
