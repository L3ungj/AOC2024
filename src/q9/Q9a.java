package src.q9;

import src.utils.InputFileReader;

public class Q9a {
    public static void main(String[] args) {
        String line = InputFileReader.readLine();
        int[] a = line.chars().map(x -> x - '0').toArray();
        int j = a.length - 1;
        int pos = 0;
        long ans = 0;
        boolean done = false;
        for(int i=0;i<a.length && !done && i<=j;++i) {
            if(i % 2 == 0) {
                for(int k=0;k<a[i];++k) 
                    ans += (pos++) * (i/2);
                continue;
            }
            for(int k=0;k<a[i];++k) {
                if(a[j] > 0) {
                    --a[j];
                    ans += (pos++) * (j/2);
                } else {
                    j -= 2;
                    if(j < i) {
                        done = true;
                        break;
                    }
                    ans += (pos++) * (j/2);
                    --a[j];
                }
            }
        }
        System.out.println(ans);
    }
}
