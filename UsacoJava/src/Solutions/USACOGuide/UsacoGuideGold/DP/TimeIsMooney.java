package Solutions.USACOGuide.UsacoGuideGold.DP;

import java.io.*;
import java.util.*;
/*
USACO 2020 January Contest, Gold
Problem 1. Time is Mooney
USACO Gold Guide - Intro DP - "Easy"
 */
public class TimeIsMooney {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static int C;
    static int maxDays;
    static ArrayList<Integer>[] city;
    static int mooney[];
    static int best[];
    static int mooneyDP[][];
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("time.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("time.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        maxDays = (500/C)+10;
        city = new ArrayList[N+1];
        mooney = new int[N+1];
        best = new int[N+1];
        mooneyDP = new int[N+1][maxDays+1];
        for (int i=1;i<=N;i++) {
            city[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            mooney[i] = Integer.parseInt(st.nextToken());
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            city[u].add(v);
        }
        //out.println(Arrays.toString(mooney));
        //logic
        Queue<Entry> BFS = new LinkedList<Entry>();
        BFS.add(new Entry(1, 0));
        while (!BFS.isEmpty()){
            Entry next = BFS.poll();
            if (next.day >= maxDays) break;
            for (int child : city[next.node]) {
                int newMooney = mooneyDP[next.node][next.day] + mooney[child];
                if (newMooney > best[child]) {
                    best[child] = newMooney;
                    mooneyDP[child][next.day+1] = Math.max(mooneyDP[child][next.day+1], newMooney);
                    BFS.add(new Entry(child, next.day+1));
                }
            }
        }
        //turn in answer
        int ans = 0;
        for (int i=0;i<=maxDays;i++) {
            ans = Math.max(ans, mooneyDP[1][i] - C*i*i);
        }
        out.println(ans);
        out.close();
    }
    static class Entry {
        int day;
        int node;
        public Entry(int n, int d){
            node=n;
            day=d;
        }
    }
}
