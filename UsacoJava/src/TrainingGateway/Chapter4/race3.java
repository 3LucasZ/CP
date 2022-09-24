package TrainingGateway.Chapter4;

import java.io.*;
import java.util.*;
/*
PROB: race3
LANG: JAVA
*/
public class race3 {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] graph = new ArrayList[51];

    public static void main(String[] args) throws IOException {
        //parse
        setup("race3");
        N=1; for (int i=1;i<=50;i++) graph[i] = new ArrayList<>();
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            boolean fin = false;
            while (true){
                int v = Integer.parseInt(st.nextToken());
                if (v<0){
                    if (v==-1) fin=true;
                    break;
                }
                graph[N].add(v+1);
            }
            if (fin) {
                N--;
                break;
            }
            N++;
        }
        if (debug) {
            System.out.println("Nodes: "+N);
            System.out.println("Graph: "+Arrays.toString(graph));
        }

        //DFS from start N times blocking a particular node to check if unavoidable
        ArrayList<Integer> unavoidable = new ArrayList<>();
        for (int block=2;block<=N-1;block++){
            visited = new boolean[N+1]; visited[block]=true;
            dfs(1);
            if (!visited[N]) unavoidable.add(block);
        }
        out.print(unavoidable.size()); for (int i : unavoidable) out.print(" "+(i-1)); out.println();

        //try each node as cut
        ArrayList<Integer> canCut = new ArrayList<>();
        for (int cut : unavoidable){
            visited = new boolean[N+1]; visited[cut]=true;
            dfs(1);
            HashSet<Integer> left = new HashSet<>(); for (int i=1;i<=N;i++) if (visited[i]) left.add(i);

            visited = new boolean[N+1];
            dfs(cut);
            HashSet<Integer> right = new HashSet<>(); for (int i=1;i<=N;i++) if (visited[i]) right.add(i);

            //if left track is connected to right track, skip
            boolean disjoint = true;
            for (int i : left) if (right.contains(i)&&i!=cut) disjoint=false;
            if (disjoint) canCut.add(cut);

        }
        out.print(canCut.size()); for (int i : canCut) out.print(" "+(i-1)); out.println();

        out.close();
    }
    static boolean[] visited;
    public static void dfs(int node){
        visited[node]=true;
        for (int child : graph[node]) if (!visited[child]) dfs(child);
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