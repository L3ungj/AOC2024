package src.q5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import src.utils.InputFileReader;
import src.utils.Utils;

public class Q5b {
    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        boolean rules = true;
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int ans = 0;
        for(String l : lines) {
            if(rules) {
                if(l.equals("")) {
                    rules = false;
                    continue;
                }
                Integer[] a = Utils.splitMapInt(l, "[|]");
                if(map.get(a[0]) == null) map.put(a[0], new HashSet<>());
                map.get(a[0]).add(a[1]);
            } else {
                Integer[] a = Utils.splitMapInt(l, ",");
                int n = a.length;
                boolean ok = true;
                for(int i=0;i<n;++i) {
                    for(int j=i+1;j<n;++j) {
                        if(!map.get(a[i]).contains(a[j])) ok = false;
                    }
                }
                if(!ok) {
                    Arrays.sort(a, (x, y) -> x.equals(y) ? 0 : map.get(x).contains(y) ? -1 : 1);
                    ans += a[n/2];
                }
            }
        }
        System.out.println(ans);
    }
}
