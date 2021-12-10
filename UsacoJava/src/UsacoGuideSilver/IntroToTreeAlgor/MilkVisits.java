package UsacoGuideSilver.IntroToTreeAlgor;/*
USACO 2019 December Contest, Silver
Problem 3. Milk Visits
USACO Guide Silver - Intro to Tree Algors Easy
Concepts: UsacoGuideSilver.DFS, Components, disparity, single indexed
 */

import java.util.*;
import java.io.*;
public class MilkVisits {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader f;
    //param
    static int N, M, num = 1;
    static boolean[] type; //true for G, false for H
    static int[] comp;
    static List<Integer>[] adj;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            f = new BufferedReader(new FileReader("milkvisits.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        } else {
            f = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(f.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        type = new boolean[N];
        comp = new int[N];
        adj = new List[N];

        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        String line = f.readLine();
        for (int i = 0; i < N; i++) {
            type[i] = line.charAt(i) == 'G';
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(f.readLine());
            int a, b;
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
        //logic
        for (int i = 0; i < N; i++) {
            dfs(i);
            num++;
        }
        for (int i=0;i<M;i++) {
            int a, b;
            boolean c;
            st = new StringTokenizer(f.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = st.nextToken().charAt(0) == 'G';

            if (comp[a] != comp[b] || type[a] == c) out.print(1);
            else out.print(0);
        }
        out.close();
        f.close();
    }
    public static void dfs(int f) {
        if (comp[f] > 0) return;
        comp[f] = num;
        for (int i : adj[f]) {
            if (type[i] == type[f]) dfs(i);
        }
    }
}
