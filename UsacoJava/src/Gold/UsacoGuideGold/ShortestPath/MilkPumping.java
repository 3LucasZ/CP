package Gold.UsacoGuideGold.ShortestPath;

import java.io.*;
import java.util.*;
/*
USACO 2019 December Contest, Gold
Problem 1. Milk Pumping
USACO Gold Guide - Shortest Paths with Non-negative edge weights - Easy
Thoughts:
Super easy Djikstra
O(N*(N+MlogN)) works
for every >= flow rate create a pipe graph and run djikstra
keep track of max (min flow rate over system/system cost)
 */
public class MilkPumping {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static ArrayList<Pipe>[] adjList;
    static Integer[] flowRates;
    //helper
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("pump.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("pump.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1]; for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        //MISTAKE: accidentally did new Integer[N+1]! THESE MISTAKES COST YOU!
        flowRates = new Integer[M+1];
        for (int i=0;i<M;i++){
            st= new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            int flow = Integer.parseInt(st.nextToken());
            adjList[u].add(new Pipe(v,cost,flow));
            adjList[v].add(new Pipe(u,cost,flow));
            flowRates[i] = flow;
        }
        //logic
        double ans = -INF;
        //for each flow rate, construct graph where all pipes have >= flow
        ArrayList<Pipe>[] tempAdjList = new ArrayList[N+1];
        for (int i=0;i<M;i++){
            for (int j=1;j<=N;j++) tempAdjList[j] = new ArrayList<>();
            for (int u=1;u<=N;u++){
                for (Pipe v : adjList[u]) {
                    if (v.flow >= flowRates[i]) tempAdjList[u].add(v);
                }
            }
            ans = Math.max(ans, (double)flowRates[i]/Djikstra(tempAdjList));
        }

        //turn in answer
        out.println((int)(ans*1e6));
        out.close();
    }
    public static int Djikstra(ArrayList<Pipe>[] adjList){
        //init
        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b)->a.distance-b.distance);
        boolean[] processed = new boolean[N+1];
        int[] cost = new int[N+1];
        for (int i=1;i<=N;i++) cost[i] = INF;

        //add source node
        cost[1]=0;
        pq.add(new Pair(1, 0));

        while (!pq.isEmpty()){
            Pair next = pq.poll();
            if (processed[next.node]) continue;
            processed[next.node]=true;
            for (Pipe child : adjList[next.node]) {
                if (cost[next.node]+child.cost < cost[child.to]) {
                    cost[child.to] = cost[next.node] + child.cost;
                    pq.add(new Pair(child.to, cost[child.to]));
                }
            }
        }
        return cost[N];
    }
    private static class Pair {
        int node;
        int distance;
        public Pair(int node, int distance){
            this.node=node;
            this.distance=distance;
        }
    }
    private static class Pipe {
        int to;
        int cost;
        int flow;
        public Pipe (int t, int c, int f){
            to=t;
            cost=c;
            flow=f;
        }
    }
}