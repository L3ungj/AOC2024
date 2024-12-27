package src.q25;

import java.util.ArrayList;

import src.utils.InputFileReader;

public class Q25a {
    public static void main(String[] args) {
        ArrayList<int[]> keys = new ArrayList<>(), locks = new ArrayList<>();
        String[] lines = InputFileReader.readAllLines();
        for (int i = 0; i < lines.length / 8 + 1; ++i) {
            int[] a = new int[5];
            for (int j = 1; j < 6; ++j) {
                for (int k = 0; k < 5; ++k) {
                    if (lines[i * 8 + j].charAt(k) == '#') {
                        a[k]++;
                    }
                }
            }
            if (lines[i * 8].charAt(0) == '#') {
                keys.add(a);
            } else {
                locks.add(a);
            }
        }
        int ans = 0;
        for(int[] key: keys) {
            for(int[] lock: locks) {
                boolean ok = true;
                for(int i = 0; i < 5; ++i) {
                    if(key[i] + lock[i] > 5) {
                        ok = false;
                        break;
                    }
                }
                if(ok) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
