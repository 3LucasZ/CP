package Silver.Training;

import java.io.*;
import java.util.*;
/*
MemSQL Start[c]UP 3.0 - Round 1
E. Desk Disorder
I dont want to commentate on this problem... hahaha but:
took 3 hours
"cereal" type problem
solved on own :)
THE GOLD:
node -> node is x1
tree + backedge -> x2
tree -> nodes in tree

 */
public class DeskDisorder {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int[] head;
    static ArrayList<Integer>[] tree;
    static ArrayList<Integer>[] adjList;

    //ff comps
    static boolean[] visited;
    static int[] color;
    static int[] type;
    static boolean[] isColorCyclic;

    //tree dp
    static long ans = 1;
    static long[][] dp;

    //help
    static long MOD = (long)1e9+7;

    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        head = new int[2*N+1];
        adjList = new ArrayList[2*N+1];
        tree = new ArrayList[2*N+1];
        for (int i=1;i<=2*N;i++) adjList[i] = new ArrayList<>();
        for (int i=1;i<=2*N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            head[u]=v;
            adjList[u].add(v);
            adjList[v].add(u);
            tree[v].add(u);
        }
        if (debug) System.out.println(Arrays.toString(adjList));

        //ff comps
        visited = new boolean[2*N+1];
        color = new int[2*N+1];
        type = new int[2*N+1];
        isColorCyclic = new boolean[2*N+1];
        for (int i=1;i<=2*N;i++){
            if (adjList[i].size()==0) continue;
            if (!visited[i]) floodfill(i, 0, i);
        }
        if (debug) System.out.println(Arrays.toString(color));
        if (debug) System.out.println(Arrays.toString(isColorCyclic));

        //get tree roots
        ArrayList<Integer> roots = new ArrayList<>();
        for (int i=1;i<=2*N;i++){
            if (head[i]==0 && tree[i].size()>=1) roots.add(i);
        }
        if (debug) System.out.println(roots);

        //tree dp
        dp = new long[2*N+1][2];
        for (int root : roots){
            waysToMove(root, 0);
            ans = (ans * dp[root][1])%MOD;
        }

        if (debug) for (int i=1;i<=2*N;i++) System.out.println(dp[i][0]+ " and "+ dp[i][1]);
        //turn in answer
        for (int i=1;i<=2*N;i++) if (type[i]==2) ans = (ans*2)%MOD;
        out.println(ans);
        out.close();
    }
    public static void waysToMove(int node, int par){
        //base case: leaf
        if (tree[node].size()==0) {
            dp[node][0]=1;
            dp[node][1]=1;
            return;
        }

        //calculate sub problems
        for (int child : tree[node]){
            waysToMove(child, node);
        }

        //look up table to do dp[0][a]xdp[0][b]x...xdp[1][k]x...dp[0][z] in constant time
        long[] multL = new long[tree[node].size()+1];
        multL[0]=1;
        for (int i=1;i<=tree[node].size();i++){
            multL[i] = (multL[i-1]*dp[tree[node].get(i-1)][0])%MOD;
        }
        if (debug) System.out.println(Arrays.toString(multL));

        long[] multR = new long[tree[node].size()+1];
        multR[tree[node].size()]=1;
        for (int i=tree[node].size()-1;i>=0;i--){
            multR[i] = (multR[i+1]*dp[tree[node].get(i)][0])%MOD;
        }
        if (debug) System.out.println(Arrays.toString(multR));

        //dp to solve current problem
        dp[node][0] = multL[tree[node].size()];
        dp[node][1] = dp[node][0];
        for (int exclude=0;exclude<tree[node].size();exclude++){
            dp[node][1] = (dp[node][1]+(multL[exclude]*dp[tree[node].get(exclude)][1])%MOD*multR[exclude+1])%MOD;
        }
    }

    public static void floodfill(int node, int par, int col){
        if (visited[node]) {
            if (head[node]==node) type[col]=1;
            if (type[col]==0) type[col]=2;
            return;
        }
        color[node]=col;
        visited[node]=true;
        for (int child : adjList[node]){
            if (child!=par) floodfill(child, node, col);
        }
    }
}
