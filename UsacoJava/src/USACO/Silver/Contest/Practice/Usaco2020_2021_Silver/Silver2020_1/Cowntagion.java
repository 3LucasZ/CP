package USACO.Silver.Contest.Practice.Usaco2020_2021_Silver.Silver2020_1;

import java.io.*;
import java.util.*;
/*
USACO 2020 December Contest, Silver

Problem 1. Cowntagion
 */
public class Cowntagion {
    //io
    static BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    //param
    static int N;
    static ArrayList<Integer>[] adj;

    //static int count = 0;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(f.readLine());
        adj = new ArrayList[N+1];
        for (int i=0;i<N+1;i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i=0;i<N-1;i++) {
            StringTokenizer st = new StringTokenizer(f.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        //logic
        int ans = 0;
        for (int i=1;i<=N;i++) {
            ans += days(i);
        }
        out.println(ans);
        out.close();
        f.close();
    }
    public static int days(int i) {
        int count = 0;
        if (i == 1) count = 1;
        int days = 0;
        int infected = 1;
        for (int node : adj[i]) {
            count ++;
        }
        while (infected < count) {
            infected *= 2;
            days ++;
        }
        days += count - 1;
        //out.println(days);
        return days;
    }
}
