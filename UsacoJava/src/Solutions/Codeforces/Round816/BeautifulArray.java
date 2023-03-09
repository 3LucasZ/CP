package Solutions.Codeforces.Round816;

import java.io.*;
import java.util.*;

/*
PROB: BeautifulArray
LANG: JAVA
*/
public class BeautifulArray {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("BeautifulArray");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long K = Integer.parseInt(st.nextToken());
        long B = Integer.parseInt(st.nextToken());
        long S = Long.parseLong(st.nextToken());

        //* greedy/constructive
        long[] ans = new long[N];
        long firstTake = B*K;
        ans[0]=firstTake;
        long rem = S-B*K;
        for (int i=0;i<N;i++){
            if (rem<0) break;
            long nextTake = Math.min(rem,K-1);
            rem-=nextTake;
            ans[i]+=nextTake;
        }
        if (rem==0){
            for (int i=0;i<N;i++) out.print(ans[i]+" ");
            out.println();
        } else {
            out.println(-1);
        }
    }


    static BufferedReader br;
    static PrintWriter out;

    public static void setup(String fileName) throws IOException {
        if (submission) {
            br = new BufferedReader(new FileReader(fileName + ".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".out")));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
    }
}