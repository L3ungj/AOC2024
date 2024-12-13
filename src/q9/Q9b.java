package src.q9;

import java.util.PriorityQueue;

import src.utils.InputFileReader;

public class Q9b {
    public static void main(String[] args) {
        String line = InputFileReader.readLine();
        int[] a = line.chars().map(x -> x - '0').toArray();
        @SuppressWarnings("unchecked")
        PriorityQueue<Integer>[] pq = (PriorityQueue<Integer>[]) new PriorityQueue[10];
        for(int i=0;i<10;++i) pq[i] = new PriorityQueue<>();
        int[] stPos = new int[a.length];
        int pos = 0;
        for(int i=0;i<a.length;++i) {
            stPos[i] = pos;
            pos += a[i];
            if(i % 2 == 1) 
                pq[a[i]].add(i);
        }
        long ans = 0;
        for(int i=a.length - 1; i>=0; i -= 2) {
            int min_k = -1, min_j = i+1;
            for(int k = a[i]; k < 10; ++k) {
                if(pq[k].peek() != null && pq[k].peek() < min_j) {
                    min_j = pq[k].peek();
                    min_k = k;
                }
            }
            if(min_j > i) {
                for(int p=0; p<a[i]; ++p) {
                    ans += (stPos[i] + p) * (i/2);
                }
                continue;
            }
            pq[min_k].poll();
            int new_k = min_k - a[i];
            pq[new_k].add(min_j);
            for(int p = 0; p<a[i]; ++p) {
                ans += (stPos[min_j] + p) * (i/2);
            }
            stPos[min_j] += a[i];
        }
        System.out.println(ans);
    }
}
