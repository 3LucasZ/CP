package Solutions.CodeJam.Qual_2022;

import java.io.*;
import java.util.*;

public class TwistyLittlePassages {
    static boolean submission = false;
    static boolean debug = true;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup();
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static int K;

    static int cur;
    static boolean[] vis;
    static long tsum;
    static long wsum;

    public static void solve(int tcs) throws IOException {
        //* parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); int b = Integer.parseInt(st.nextToken()); //tmp throwaway
        //* interactive
        cur = 1;
        vis = new boolean[N+1];
        tsum = 0;
        wsum = 0;
        for (int i=0;i<K;i++){
            // teleport
            if (i%2==0){
                while (vis[cur]) cur++;
                vis[cur]=true;
                System.out.println("T "+cur);
                st = new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                int edges = Integer.parseInt(st.nextToken());
                tsum += edges;
            }
            //walk
            else {
                System.out.println("W");
                st =new StringTokenizer(br.readLine());
                int node = Integer.parseInt(st.nextToken());
                int edges = Integer.parseInt(st.nextToken());
                if (!vis[node]) {
                    vis[node] = true;
                    wsum += edges;
                }
            }
        }
        //* make the guess!
        long tavg = tsum/(K/2);
        long ans = tavg*(N-K/2) + wsum;
        System.out.println("E "+ans/2);
    }

    static BufferedReader br;
    static PrintWriter out;

    public static void setup() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
    }
}