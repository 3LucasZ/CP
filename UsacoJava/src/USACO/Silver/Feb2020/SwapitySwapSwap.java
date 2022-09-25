package USACO.Silver.Feb2020;

import  java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Silver
Problem 1. Swapity Swapity Swap
Concept: Cycles, mod, simpole dynamic programming
 */
public class SwapitySwapSwap {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int n;
    static int m;
    static int k;
    static int[] orig;
    static Int[] len;
    static int[] ans;
    static boolean[] found;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("swap.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("swap.out")));
        }
        else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        orig = new int[n+1];
        for (int i=1;i<=n;i++) {
            orig[i] = i;
        }
        //out.println(Arrays.toString(orig));
        for (int i=0;i<m;i++) {
            st = new StringTokenizer(f.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            for (int j=start;j<=(end+start)/2;j++) {
                int u = orig[j];
                int v = orig[end-(j-start)];
                orig[j] = v;
                orig[end-(j-start)] = u;
            }
        }
        //out.println(Arrays.toString(orig));
        len = new Int[n+1];
        ans = new int[n+1];
        found = new boolean[n+1];
        for (int i=1;i<=n;i++) {
            if (!found[i]) {
                int t = i;
                Int cycle = new Int(0);
                while (true) {
                    //reference trick
                    len[t] = cycle;
                    found[t] = true;
                    //basic
                    cycle.i++;
                    t = orig[t];
                    if (t==i) break;
                }
                t=i;
                for (int c=0;c<k%len[i].i;c++) {
                    t=orig[t];
                }
                ans[i] = t;
                t=i;
                for (int c=1;c<len[i].i;c++){
                    ans[orig[t]] = orig[ans[t]];
                    t=orig[t];
                }
            }
        }
        //logic
        for (int i=1;i<=n;i++) {
            out.println(ans[i]);
        }
        out.close();
        f.close();
    }
    public static class Int {
        int i;
        public Int(int i1) {i=i1;};
        public String toString() {
            return ""+i;
        }
    }
}
