package CodeJam.Round2_2022;

import java.io.*;
import java.util.*;

public class SavingTheJelly {
    static boolean submission = true;
    static boolean debug = false;

    public static void main(String[] args) throws IOException {
        //* handle TCS
        setup("io/tmp");
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i <= T; i++) solve(i);
        out.close();
    }

    static int N;
    static Point special;
    static Point[] candies;
    static Point[] children;

    static int[] childToCandy;
    static int[] candyToChild;

    static PriorityQueue<Integer>[] childCandies;

    static boolean[] visited;

    public static void solve(int tcs) throws IOException {
        print("Case #" + tcs + ": "); if (debug) println("");
        //* parse
        N = Integer.parseInt(br.readLine());
        children = new Point[N+1];
        candies = new Point[2*N+1];
        for (int i=1;i<=N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            children[i] = new Point(x,y,i);
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        special = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);
        for (int i=N+1;i<=2*N;i++) {
            st = new StringTokenizer(br.readLine());
            long x = Integer.parseInt(st.nextToken());
            long y = Integer.parseInt(st.nextToken());
            candies[i] = new Point(x,y,i-N);
        }
        candies[0] = special;
        //* create bipartite graph of children(1..N) - candy(N+1..2N) with source 2N+1 and sink 2N+2
        MaxFlow maxFlow = new MaxFlow(2*N+2,2*N+1,2*N+2);
        //match source to 1..N
        for (int i=1;i<=N;i++) maxFlow.addEdge(2*N+1,i,1);
        //match sink to N+1..2N
        for (int i=N+1;i<=2*N;i++) maxFlow.addEdge(i,2*N+2,1);
        //match child to candy if dist(u,v)<=dist(u,special)
        for (int u=1;u<=N;u++){
            for (int v=N+1;v<=2*N;v++){
                if (dist(children[u],candies[v])<=dist(children[u],special)) {
                    maxFlow.addEdge(u,v,1);
                }
            }
        }
        //* run maxFlow
        long flow = maxFlow.solve();
        if (flow!=N) {
            println("IMPOSSIBLE");
            return;
        }
        println("POSSIBLE");
        if (debug) maxFlow.print();
        //* extract maxFlow edges
        childToCandy = new int[N+1];
        candyToChild = new int[2*N+1];
        for (int child=1;child<=N;child++){
            for (Edge edge : maxFlow.graph[child]){
                if (edge.cap==0 && edge.residual.cap==1) {
                    childToCandy[child] = edge.v;
                    candyToChild[edge.v] = child;
                }
            }
        }
        if (debug) println("match: "+ Arrays.toString(childToCandy));
        visited = new boolean[2*N+1];
        //* clever construction
        //imagine the duality function where match(child) = matched candy and closest(child) = closest candy
        //find cycles and solve for them
        childCandies = new PriorityQueue[N+1];
        for (int i=1;i<=N;i++) {
            int finalI = i;
            childCandies[i] = new PriorityQueue<>(Comparator.comparingLong(a -> dist(children[finalI], candies[a])));
        }
        for (int child=1;child<=N;child++) for (int candy=N+1;candy<=2*N;candy++) childCandies[child].add(candy);
        for (int u=1;u<=N;u++){
            while (!visited[u]) {
                //find beginning of a cycle (arbitrary u)
                boolean[] iterationVisited = new boolean[2*N+1];
                int cur = u;
                do {
                    int candy = getClosestCandy(cur);
                    iterationVisited[candy]=true;
                    iterationVisited[cur]=true;
                    cur = candyToChild[candy];
                } while (!iterationVisited[cur]);
                //cur is now the beginning of the cycle
                //solve the cycle and ret them
                do {
                    int candy = getClosestCandy(cur);
                    println(cur + " " + (candy-N+1));
                    visited[candy]=true;
                    visited[cur]=true;
                    cur = candyToChild[candy];
                } while (!visited[cur]);
            }
        }
    }
    public static int getClosestCandy(int child){
        while (!childCandies[child].isEmpty()) {
            if (visited[childCandies[child].peek()]) childCandies[child].poll();
            else break;
        }
        return childCandies[child].peek();
    }
    public static long dist(Point u, Point v){
        return (v.x-u.x)*(v.x-u.x)+(v.y-u.y)*(v.y-u.y);
    }

    private static class Point {
        long x;
        long y;
        int id;
        public Point(long x, long y, int id){
            this.x=x;
            this.y=y;
            this.id=id;
        }
    }

    private static class MaxFlow {
        /*
        Conditions:
        directed graph
        1 indexed graph
        list storage
        */
        int N;
        int S;
        int T;

        ArrayList<Edge>[] graph;
        boolean[] visited;

        static final int INF = Integer.MAX_VALUE/2;

        public MaxFlow(int N, int S, int T){
            this.N=N;
            this.S=S;
            this.T=T;
            graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<>();
            visited = new boolean[N+1];
        }
        public void addEdge(int u, int v, int c){
            Edge forward = new Edge(u,v,c);
            Edge residual = new Edge(v,u,0);
            graph[u].add(forward);
            graph[v].add(residual);
            forward.residual=residual;
            residual.residual=forward;
        }
        public long solve(){
            long maxFlow = 0;
            long flow = 0;
            while (true){
                visited = new boolean[N+1];
                flow = dfs(S,INF);
                if (flow==0) break;
                maxFlow+=flow;
            }
            return maxFlow;
        }
        public long dfs(int node, long flow){
            //found augmenting path
            if (node==T) return flow;
            visited[node]=true;
            for (Edge child : graph[node]){
                if (child.cap>0 && !visited[child.v]){
                    long pathCap = dfs(child.v,Math.min(flow,child.cap));
                    //augmenting path exists
                    if (pathCap > 0){
                        child.augment(pathCap);
                        return pathCap;
                    }
                }
            }
            return 0;
        }
        public void print(){
            for (int i=1;i<=N;i++){
                println(String.valueOf(graph[i]));
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int cap;
        Edge residual;

        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.cap=c;
        }
        public void augment(long pathCap){
            cap-=pathCap;
            residual.cap+=pathCap;
        }
        public String toString(){
            return "["+u+", "+v+" : "+cap+"]";
        }
    }
    public static void print(Object str){
        if (debug) System.out.print(str);
        else out.print(str);
    }
    public static void println(Object str){
        if (debug) System.out.println(str);
        else out.println(str);
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