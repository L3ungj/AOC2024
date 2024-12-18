package src.utils;

public class PairInt implements Comparable<PairInt> {
    public int x = 0, y = 0;

    public PairInt() {}

    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(PairInt a) {
        return x == a.x && y == a.y;
    }

    public int compareTo(PairInt a) {
        if(x == a.x) return Integer.compare(y, a.y);
        return Integer.compare(x, a.x);
    }

    public static PairInt add(PairInt a, PairInt b) {
        return new PairInt(a.x + b.x, a.y + b.y);
    }
}
