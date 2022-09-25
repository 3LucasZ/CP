package USACOGuide.UsacoGuideGold.TopologicalSort;

import java.io.*;
import java.util.*;
/*
USACO 2018 US Open Contest, Gold
Problem 2. Milking Order
USACO Gold Training
USACO Gold Guide - Top Sort - Normal
Very elegant top sort problem!
part 1: binary search on # of consecutive logs until the logic breaks
part 2: construct a DAG
part 3: run a BFS style top sort, however we want it lexicographical order
    this is the elegant part! we use a Priority Queued BFS.
    lemma 1: add edges when their inEdge count is 0.
    lemma 2: always grab the smallest id cow when polling.
 */
public class MilkingOrder {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;
    static ArrayList<Integer>[] logs;

    static ArrayList<Integer>[] DAG;

    public static void main(String[] args) throws IOException {
        setup("milkorder");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        logs = new ArrayList[M];
        for (int i=0;i<M;i++) logs[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int j=0;j<m;j++){
                logs[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        int lo=0; int hi=M-1;
        while (lo < hi){
            int mid = (lo+hi+1)/2;
            if (validX(mid)) lo=mid;
            else hi=mid-1;
        }
        if (debug) {
            System.out.println(lo);
            //for (int i=0;i<M;i++) System.out.println(validX(i));
        }
        validX(lo);

        int[] inEdges = new int[N+1];
        for (int i=1;i<=N;i++){
            for (int child : DAG[i]){
                inEdges[child]++;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue();
        boolean[] visited = new boolean[N+1];
        for (int i=1;i<=N;i++) if (inEdges[i]==0) {
            pq.add(i);
            visited[i]=true;
        }

        if (debug){
            System.out.println(Arrays.toString(inEdges));
            System.out.println(pq);
        }

        for (int i=1;i<=N;i++) {
            int next = pq.poll();
            out.print(next);
            if (i!=N) out.print(" ");
            for (int child : DAG[next]){
                if (visited[child]) continue;
                inEdges[child]--;
                if (inEdges[child]!=0) continue;
                pq.add(child);
                visited[child]=true;
            }
        }
        out.println();
        out.close();
    }
    static boolean[] onStack;
    static boolean[] visited;
    static boolean cyclic = false;
    static boolean validX(int X){
        //create graph based on x logs
        DAG = new ArrayList[N+1];
        for (int i=1;i<=N;i++) DAG[i] = new ArrayList<>();
        for (int i=0;i<=X;i++){
            for (int j=1;j<logs[i].size();j++){
                DAG[logs[i].get(j-1)].add(logs[i].get(j));
            }
        }
        //if (debug) System.out.println(Arrays.toString(DAG));

        //test if its a dag
        cyclic = false;
        visited = new boolean[N+1];
        onStack = new boolean[N+1];
        for (int i=1;i<=N;i++){
            if (!visited[i]) DFS(i);
        }
        return !cyclic;
    }
    static void DFS(int node){
        if (onStack[node]) cyclic = true;
        if (visited[node]) return;
        visited[node]=true;
        onStack[node]=true;
        for (int child : DAG[node]){
            DFS(child);
        }
        onStack[node]=false;
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
