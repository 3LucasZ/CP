package Solutions.CodeJam.Round1C_2022;

import java.io.*;
import java.util.*;

public class Squary {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int K;
    static long[] X;
    public static void solve(int tcs) throws IOException {
        System.out.print("Case #" + tcs + ": ");
        if (debug) System.out.println();
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        X = new long[N];
        for (int i=0;i<N;i++) X[i] = Long.parseLong(st.nextToken());
        //* calc
        long S = S(X);
        long SP = SP(X);

        //* case: K=1 so N1 = -SP(X)/S(X)
        if (K==1){
            if (SP==0) System.out.println(0);
            else if (S==0 || SP%S!=0) System.out.println("IMPOSSIBLE");
            else System.out.println(-SP/S);
        }
        //* case: K>=2 so N1 = 1-S(X) and N2 = -SP(X+[N1])
        else {
            long N1 = 1-S;
            long N2 = -SP-(1-S)*S;
            System.out.println(N1+" "+N2);
        }
    }

    public static long SP(long[] a){
        long ret = 0;
        for (int i=0;i<a.length;i++){
            for (int j=0;j<a.length;j++){
                if (i==j) continue;
                ret+=a[i]*a[j];
            }
        }
        return ret/2;
    }
    public static long S(long[] a){
        long ret = 0;
        for (int i=0;i<a.length;i++) {
            ret+=a[i];
        }
        return ret;
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}