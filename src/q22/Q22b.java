package src.q22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.utils.InputFileReader;

public class Q22b {
    static Long next(Long n) {
        Long n1 = (n ^ (n * 64)) % 16777216;
        Long n2 = (n1 ^ (n1 / 32)) % 16777216;
        Long n3 = (n2 ^ (n2 * 2048)) % 16777216;
        return n3;
    }

    public static void main(String[] args) {
        String[] lines = InputFileReader.readAllLines();
        Long ans = 0L;
        HashMap<List<Integer>, Integer> totMap = new HashMap<>();
        for (String s : lines) {
            Long n = Long.parseLong(s);
            Integer[] difs = new Integer[4];
            Long prev = n;
            HashMap<List<Integer>, Integer> map = new HashMap<>();
            for(int i = 0; i < 3; i++) {
                n = next(n);
                int dif = (int)(n % 10 - prev % 10);
                difs[i+1] = dif;
                prev = n;
            }
            for(int t = 0; t < 1997; ++t) {
                n = next(n);
                for(int i=0;i<3; ++i) {
                    difs[i] = difs[i+1];
                }
                difs[3] = (int)(n % 10 - prev % 10);
                List<Integer> l = List.of(difs);
                if(map.get(l) == null) {
                    map.put(l, (int)(n % 10));
                } 
                prev = n;
            }
            for(Map.Entry<List<Integer>, Integer> entry : map.entrySet()) {
                if(totMap.get(entry.getKey()) == null) {
                    totMap.put(entry.getKey(), entry.getValue());
                } else {
                    totMap.put(entry.getKey(), entry.getValue() + totMap.get(entry.getKey()));
                }
            }
        }
        for(Map.Entry<List<Integer>, Integer> entry : totMap.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }
        System.out.println(ans);
        // for(Map.Entry<List<Integer>, Integer> entry : totMap.entrySet()) {
        //     if((int)entry.getValue() == ans) {
        //         System.out.println(entry.getKey());
        //     }
        // }
    }
}
