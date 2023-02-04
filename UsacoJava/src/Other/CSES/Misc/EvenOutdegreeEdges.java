package Other.USACOGuide.UsacoGuideSilver.IntroToTreeAlgor;

import java.io.*;
import java.util.*;
/*
CSES Problem Set
Even Outdegree Edges
USACO Silver Guide - Trees - Hard
Thoughts:
looked at solution numerous times also saw beautiful post: https://codeforces.com/blog/entry/68138
the graph given MAY be disconnected, WLOG the graph is connected.
first, create a dfs tree for the graph
all non span-edges are directed upwards to an ancestor
keep track of the parity of each node at every stage
now we have a tree, with an arbitrary parity at each node
each node has 1 parent (except root)
direct this span-edge wisely. if leaf is odd point it up else point it down.
change the parities accordingly to this operation
now every edge has even outdegree edges (think about it) except for the root, maybe
if the root has even outdegree edges, you win, else impossible (odd #edges)

VERY BEAUTIFUL PROBLEM!!! The main takeaway is converting a graph to its dfs-span-tree to simplify the problem.
since each node has 1 parent, we can toggle parities greedily until we see the state of the root.
 */
public class EvenOutdegreeEdges {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int M;

    //graphing
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] retGraph;

    //helper
    static boolean[] oddParity;
    static int[] visited;
    static int timer=1;

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        retGraph = new ArrayList[N+1];
        oddParity = new boolean[N+1];

        for (int i=1;i<=N;i++) {
            graph[i]=new ArrayList<>();
            retGraph[i]=new ArrayList<>();
        }
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }
        //logic
        solve();
        if (debug){
            System.out.println(Arrays.toString(graph));
            System.out.println(Arrays.toString(retGraph));
            System.out.println(Arrays.toString(oddParity));
            System.out.println(Arrays.toString(visited));
        }
        out.close();
    }

    public static void solve() {
        visited = new int[N+1];
        for (int i=1;i<=N;i++) {
            if (visited[i]==0)dfs(i,0);
        }
        //turn in answer
        for (int i=1;i<=N;i++) {
            if (oddParity[i]) {
                out.println("IMPOSSIBLE");
                return;
            }
        }
        for (int u=1;u<=N;u++){
            for (int v=0;v<retGraph[u].size();v++){
                out.println(u+" "+retGraph[u].get(v));
            }
        }
    }
    public static void dfs(int node, int parent){
        visited[node]=timer++;

        for (int child : graph[node]) if (child!=parent){
            if (visited[child]==0){
                dfs(child,node);
                if (oddParity[child]){
                    retGraph[child].add(node);
                    oddParity[child]=!oddParity[child];
                }
                else {
                    retGraph[node].add(child);
                    oddParity[node]=!oddParity[node];
                }
            } else if (visited[node] > visited[child]){
                retGraph[node].add(child);
                oddParity[node]=!oddParity[node];
            }
        }
    }
}
