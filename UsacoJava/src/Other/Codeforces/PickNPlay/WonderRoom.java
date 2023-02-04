package Other.Codeforces.PickNPlay;

import java.io.*;
import java.util.*;
/*
PROB: WonderRoom
LANG: JAVA
*/
public class WonderRoom {
    static boolean submission = false;
    static boolean debug = true;

    static long N;
    static long A;
    static long B;
    static long INF = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //parse
        setup("WonderRoom");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        //make sure A<=B
        boolean swapped = false;
        if (B<A) {
            long tmp=A;
            A=B;
            B=tmp;
            swapped = true;
        }
        //case 1: AB>=6N
        if (A*B>=6*N) {
            out.println(A*B);
            out.println(A+" "+B);
            out.close();
        }
        //case 2: brute force A<=A'<=ceilrt(6n)
        long ans = INF;
        long AF = 0;
        long BF = 0;
        for (long A2=A;(A2-1)*(A2-1)<=6*N;A2++){
            long B2 = 6*N/A2;
            if (B2*A2<6*N) B2++;
            if (B2<B) continue;
            if (B2*A2<ans){
                ans=A2*B2;
                AF=A2;
                BF=B2;
            }
        }
        //ret
        out.println(ans);
        if (swapped) out.println(BF+" "+AF);
        else out.println(AF+" "+BF);
        out.close();
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