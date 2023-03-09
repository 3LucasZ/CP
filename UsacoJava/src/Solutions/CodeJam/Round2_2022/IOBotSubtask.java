package Solutions.CodeJam.Round2_2022;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
PROB: IOBot
LANG: JAVA
*/
public class IOBotSubtask {
    static boolean submission = false;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("IOBot");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static long C;
    static ArrayList<Long>[] zeros;
    static ArrayList<Long>[] ones;

    public static void solve(int tcs) throws IOException {
        out.print("Case #" + tcs + ": "); if (debug) out.println();
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        zeros = new ArrayList[2]; zeros[0] = new ArrayList<>(); zeros[1] = new ArrayList<>();
        ones = new ArrayList[2]; ones[0] = new ArrayList<>(); ones[1] = new ArrayList<>();
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            long position = Integer.parseInt(st.nextToken());
            int type = Integer.parseInt(st.nextToken());
            if (position<0) {
                if (type==0) zeros[1].add(-position);
                else ones[1].add(-position);
            }
            else {
                if (type==0) zeros[0].add(position);
                else ones[0].add(position);
            }
        }
        //* solve both -, + lists in disjoint manner
        long ans = solveList(0)+solveList(1);
        //* ret
        out.println(ans);
    }

    static final long INF = Long.MAX_VALUE;
    public static long solveList(int id){
        if (debug) out.println("List id: "+id);
        // sort from min to max positions
        Collections.sort(zeros[id]);
        Collections.sort(ones[id]);
        //* dp
        int A = zeros[id].size();
        int B = ones[id].size();
        if (debug) out.println("A: "+A+", B: "+B);
        zeros[id].add(0,0L);
        ones[id].add(0,0L);
        long[][] dp = new long[A+1][B+1]; for (int a=0;a<=A;a++) for (int b=0;b<=B;b++) dp[a][b]=INF;
        dp[A][B]=0;
        for (int a=A;a>=0;a--){
            for (int b=B;b>=0;b--){
                if (a>=2) dp[a-2][b]=Math.min(dp[a-2][b],dp[a][b]+2*zeros[id].get(a)+C);
                if (b>=2) dp[a][b-2]=Math.min(dp[a][b-2],dp[a][b]+2*ones[id].get(b)+C);
                if (a>=1&&b>=1)dp[a-1][b-1]=Math.min(dp[a-1][b-1],dp[a][b]+2*Math.max(zeros[id].get(a),ones[id].get(b)));
                if (a>=1)dp[a-1][b]=Math.min(dp[a-1][b],dp[a][b]+2*zeros[id].get(a));
                if (b>=1)dp[a][b-1]=Math.min(dp[a][b-1],dp[a][b]+2*ones[id].get(b));
            }
        }
        if (debug) {
            print(dp);
        }
        return dp[0][0];
    }
    private static class Ball {
        long position;
        int type;
        public Ball(int pos, int t){
            this.position=pos;
            this.type=t;
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
    public static void print(long[][] arr){
        for (int r=0;r<arr.length;r++){
            for (int c=0;c<arr[r].length;c++){
                String str = ""+arr[r][c];
                while (str.length()<5) str+=" ";
                out.print(str);
            }
            out.println();
        }
        out.println();
    }
}