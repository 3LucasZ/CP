package Helpers.PURS;

import java.io.*;

/*
PROB: BITRURS
LANG: JAVA
*/
public class BITRURS {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        setup("BITRURS");
    }
    private static class RURS {
        //1 indexed
        //inclusive range
        //only 2 operations: range sum, range add
        BIT bit1;
        BIT bit2;
        public RURS(int K){
            bit1 = new BIT(K);
            bit2 = new BIT(K);
        }
        public long sum(int l, int r){
            long rsum = (r + 1) *bit1.preSum(r)-bit2.preSum(r);
            long lsum = l*bit1.preSum(l-1)-bit2.preSum(l-1);
            return rsum-lsum;
        }
        public void add(int l, int r, int x){
            bit1.update(l,x);bit2.update(l,l*x);
            bit1.update(r+1,-x);bit2.update(r+1,-l*x);
        }
        private static class BIT {
            //must be 1 indexed
            //range sum is inclusive
            int K;
            long[] A;
            long[] bit;

            public BIT(int K) {
                this.K = K;
                bit = new long[K + 1];
                A = new long[K + 1];
            }

            public void set(int i, long val) {
                update(i, val - A[i]);
            }

            public void update(int i, long val) {
                A[i] += val;
                while (i <= K) {
                    bit[i] += val;
                    i += i & (-i);
                    //System.out.println("STUCK IN UPDATE");
                }
            }

            public long preSum(int i) {
                long sum = 0;
                while (i > 0) {
                    sum += bit[i];
                    i -= i & (-i);
                    //System.out.println("STUCK IN PRESUM");
                }
                return sum;
            }

            public long rangeSum(int lo, int hi) {
                return preSum(hi) - preSum(lo - 1);
            }
        }
    }




















    static BufferedReader br;
    static PrintWriter out;
    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName+".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}