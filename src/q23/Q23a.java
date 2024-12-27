package src.q23;

import java.util.ArrayList;
import java.util.HashMap;

import src.utils.InputFileReader;

public class Q23a {
    HashMap<String, ArrayList<String>> adj = new HashMap<>();

    int ans = 0;

    ArrayList<String> path = new ArrayList<>();
    void dfs(String u, int d) {
        if(d == 0) {
            if(!u.equals(path.get(0))) {
                return;
            }
            boolean ok = false;
            for(int i = 0; i < 3; ++i) {
                if(path.get(i).charAt(0) == 't') {
                    ok = true;
                }
            }
            if(ok) ++ans;
            return;
        }
        for(String v : adj.get(u)) {
            path.add(v);
            dfs(v, d - 1);
            path.remove(path.size() - 1);
        }
    }

    void main_() {
        String[] lines = InputFileReader.readAllLines();
        for (String line : lines) {
            String[] parts = line.split("-");
            String a = parts[0];
            String b = parts[1];
            if (!adj.containsKey(a)) {
                adj.put(a, new ArrayList<>());
            }
            adj.get(a).add(b);
            if (!adj.containsKey(b)) {
                adj.put(b, new ArrayList<>());
            }
            adj.get(b).add(a);
        }

        for(String u : adj.keySet()) {
            path.add(u);
            dfs(u, 3);
            path.remove(path.size() - 1);
        }
        System.out.println(ans / 6);
    }

    public static void main(String[] args) {
        Q23a q = new Q23a();
        q.main_();
    }
}
