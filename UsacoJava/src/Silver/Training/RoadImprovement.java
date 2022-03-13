package Silver.Training;

import java.io.*;
import java.util.*;

public class RoadImprovement {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static ArrayList<Edge>[] adjList;
    static int days = 0;
    static ArrayList<Integer>[] construction;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++) adjList[i] = new ArrayList();
        for (int i=1;i<=N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(new Edge(v, i));
            adjList[v].add(new Edge(u, i));
        }
        if (debug) System.out.println(Arrays.toString(adjList));

        //logic
        construction = new ArrayList[N+1];
        for (int i=1;i<=N;i++) construction[i] = new ArrayList<>();

        dfs(1, 0, 0);
        if (debug) {
            System.out.println("Construction: "+Arrays.toString(construction));
        }

        //turn in answer with format
        out.println(days);
        for (int i=1;i<=days;i++){
            out.print(construction[i].size()+" ");
            for (int id : construction[i]) out.print(id+" ");
            out.println();
        }
        out.close();
    }

    public static void dfs(int node, int p, int prevDay) {
        days = Math.max(days, adjList[node].size());

        int day=1;
        for (int i=0;i<adjList[node].size();i++){
            Edge child = adjList[node].get(i);
            if (child.v==p) continue;
            if (day==prevDay) day++;
            construction[day].add(child.id);
            dfs(child.v, node, day);
            day++;
        }
    }

    private static class Edge {
        int v;
        int id;
        public Edge(int v, int id){
            this.v=v;
            this.id=id;
        }
        public String toString(){
            return "["+v+": "+id+"]";
        }
    }
}
