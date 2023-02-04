package Other.Codeforces.Round838;

import java.io.*;
import java.util.*;

/*
PROB: DivideAndConquer
LANG: JAVA
*/
public class DivideAndConquer {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("DivideAndConquer");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    public static void solve(int tcs) throws IOException {
        if (debug) System.out.println("Case: " + tcs);
        //* parse
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        //* collect sum
        int sum = 0; for (int i=0;i<N;i++) sum+=A[i];
        if (sum%2==0) out.println(0);
        else {
            int minOps = Integer.MAX_VALUE;
            for (int i=0;i<N;i++){
                int init = A[i]%2;
                int cnt = 0;
                while (A[i]%2==init){
                    A[i]/=2;
                    cnt++;
                }
                minOps = Math.min(minOps,cnt);
            }
            out.println(minOps);
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