import java.io.*;
import java.util.*;
/*
CSES Problem Set
Graph Algorithms
Course Schedule
USACO Gold Guide - Topological Sort - Easy
Notes:
First every actual Topological Sort problem!
DFS on the DAG and add node to sort after all children is processed
check for cycles
- get the depth of each node
- search all the edges, is there any where the parent has more depth than the child?
print the sort
 */
public class CourseSchedule {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;
    //param
    static int N;
    static int M;
    //dfs
    static boolean[] visited;
    static ArrayList<Integer>[] adjList;
    //sort
    static boolean cyclic = false;
    static ArrayList<Integer> sort = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        adjList = new ArrayList[N+1];
        for (int i=1;i<=N;i++){
            adjList[i] = new ArrayList<>();
        }
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjList[u].add(v);
        }

        //logic: top sort
        for (int i=1;i<=N;i++){
            dfs(i);
        }
        Collections.reverse(sort);

        int[] depth = new int[N+1];
        for (int i=0;i< sort.size();i++){
            depth[sort.get(i)]=i;
        }

        //cycle IF in top sort a parent has a greater depth than a child
        for (int node=1;node<=N;node++){
            for (int child : adjList[node]){
                if (depth[node]>depth[child]) cyclic = true;
            }
        }

        //turn in answer
        if (cyclic) out.println("IMPOSSIBLE");
        else {
            for (int i=0;i<N;i++) out.print(sort.get(i)+" ");
        }
        out.close();
    }

    public static void dfs(int node){
        if (visited[node]) return;
        visited[node]=true;
        for (int child : adjList[node]){
            dfs(child);
        }
        sort.add(node);
    }
}
