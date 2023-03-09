package Solutions.USACO.Season2015_2016.Open2016.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2016 US Open Contest, Gold
Problem 2. Closing the Farm
USACO Gold Guide - DSU - Easy
Thoughts:
Nice DSU problem, work backwards strategy.
barns closed one by one same as barns added one by one.
when barn added only add edges that are relevant
modified dsu to keep track of components
YES if and only if components = 1
 */
public class ClosingTheFarm {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean submission = true;

    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] adjList;

    static int[] query;
    static boolean[] ans;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("closing.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }

        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList<>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
            adjList[v].add(u);
        }
        query = new int[N];
        for (int i=N-1;i>=0;i--){
            query[i] = Integer.parseInt(br.readLine());
        }
        if (!submission){
            System.out.println(Arrays.toString(query));
            System.out.println(Arrays.toString(adjList));
        }

        //logic
        ans = new boolean[N];
        DSU barns = new DSU(N);
        HashSet<Integer> seen = new HashSet<>();

        for (int q=0;q<N;q++){
            int u = query[q];
            barns.components++;
            seen.add(u);
            for (int v : adjList[u]){
                if (seen.contains(v)) barns.join(u, v);
            }
            if (!submission){
                System.out.println(barns.components);
            }
            ans[N-q-1] = barns.components==1;
        }

        //turn in answer
        for (boolean b : ans){
            if (b) out.println("YES");
            else out.println("NO");
        }
        out.close();
    }
    private static class DSU {
        int[] parent;
        int components = 0;

        public DSU(int nodes){
            parent = new int[nodes+1];
            for (int i=1;i<=nodes;i++) parent[i]=i;
        }

        public void join(int u, int v){
            if (connected(u,v)) return;
            int uRep = getRep(u);
            int vRep = getRep(v);
            parent[uRep]=vRep;
            components--;
        }

        public int getRep(int node){
            if (parent[node]==node) return node;
            parent[node]=getRep(parent[node]);
            return parent[node];
        }

        public boolean connected(int u, int v){
            return (getRep(u)==getRep(v));
        }
    }
}
