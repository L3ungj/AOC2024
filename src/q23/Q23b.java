package src.q23;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import src.utils.InputFileReader;

public class Q23b {
    HashMap<String, ArrayList<String>> adj = new HashMap<>();

    void bronkerbosch(Set<String> R, Set<String> P, Set<String> X) {
        if (P.isEmpty() && X.isEmpty()) {
            if(R.size() < 13) return;
            ArrayList<String> r = new ArrayList<>(R);
            r.sort(null);
            System.out.println(String.join(",", r));
            return;
        }
        Set<String> P_ = new HashSet<>(P);
        for (String u : P) {
            R.add(u);
            bronkerbosch(R, P_.stream().filter(adj.get(u)::contains).collect(Collectors.toSet()),
                    X.stream().filter(adj.get(u)::contains).collect(Collectors.toSet()));
            R.remove(u);
            P_.remove(u);
            X.add(u);
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
        bronkerbosch(new HashSet<>(), adj.keySet(), new HashSet<>());
    }

    public static void main(String[] args) {
        Q23b q = new Q23b();
        q.main_();
    }
}
