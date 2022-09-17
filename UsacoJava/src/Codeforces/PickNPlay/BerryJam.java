package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class BerryJam {
    static boolean submission = false;
    static boolean debug = true;

    static int T;

    static int N;
    static int[] l;
    static int[] r;

    static int[] ldp;
    static int[] rdp;

    static int INF = Integer.MAX_VALUE/3;

    public static void main(String[] args) throws IOException {
        //tcs handler
        setup("");
        int T = Integer.parseInt(br.readLine());
        for (int i=0;i<T;i++) out.println(solve());
        out.close();
    }
    public static int solve() throws IOException{
        //parse
        int cnt1 = 0;
        N = Integer.parseInt(br.readLine());
        l = new int[N];
        r = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=N-1;i>=0;i--){
            l[i]=Integer.parseInt(st.nextToken());
            if (l[i]==1) cnt1++;
        }
        for (int i=0;i<N;i++){
            r[i]=Integer.parseInt(st.nextToken());
            if (r[i]==1) cnt1++;
        }

        //infer
        int cnt2 = 2*N-cnt1;
        int t = cnt1 < cnt2 ? 1 : 2;
        int sum = Math.abs(cnt1-cnt2);
        int run;

        //precomp
        ldp = new int[2*N+1]; for (int i=1;i<=2*N;i++) ldp[i] = INF;
        run = 0;
        for (int i=0;i<N;i++){
            if (l[i]!=t) run++;
            else run--;
            if (run > 0) ldp[run]=Math.min(ldp[run],i+1);
        }

        rdp = new int[2*N+1]; for (int i=1;i<=2*N;i++) rdp[i] = INF;
        run = 0;
        for (int i=0;i<N;i++){
            if (r[i]!=t) run++;
            else run--;
            if (run > 0) rdp[run]=Math.min(rdp[run],i+1);
        }

        //bruteforce
        int ans = INF;
        for (int i=0;i<=sum;i++){
            int j = sum-i;
            ans=Math.min(ans, ldp[i]+rdp[j]);
        }
        return ans;
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
