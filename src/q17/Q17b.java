package src.q17;

// 2,4  rb = ra % 8
// 1,3  rb ^= 011
// 7,5  rc = ra >> rb
// 0,3  ra >>= 3
// 4,1  rb ^= rc
// 1,5  rb ^= 101
// 5,5  out rb % 8
// 3,0  loop until ra == 0

public class Q17b {
    final static int[] program = new int[] { 2, 4, 1, 3, 7, 5, 0, 3, 4, 1, 1, 5, 5, 5, 3, 0 };
    long ra = 0;

    void solve(int i) {
        if(i == -1) {
            System.out.println(ra);
            System.exit(0);
        }
        for(int x = 0; x < 8; ++x) {
            int rb = x;
            rb ^= 3;
            int rc = (int)(((ra << 3 | x) >> rb) & 7);
            rb ^= rc;
            rb ^= 5;
            // System.out.println(x + " " + rb);
            if(rb % 8 == program[i]) {
                ra = ra << 3 | x;
                solve(i-1);
                ra >>= 3;
            }
        }
    }

    public static void main(String[] args) {
        new Q17b().solve(program.length - 1);
    }
}
