package Codeforces.Round772;

import java.io.*;
import java.util.*;
/*
Codeforces Round #772 (Div. 2)
E. Cars
Thoughts:
intake input and restrictions
create a coloring for the cars (left or right)
create a directed graph for the cars (arrow means child is to the right)
top sort the cars
print the cars depth in the top sort (higher depth, more to the right)
So elegant and nice problem. Looked at solution ofc. ;|
 */
public class Cars {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int M;
    static Restriction[] restrictions;

    //coloring
    static ArrayList<Integer>[] adjList;
    static int[] orientation;
    static boolean impossible = false;

    //top sort
    static ArrayList<Integer>[] toRight;
    static boolean[] visited;
    static ArrayList<Integer> sort = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        restrictions = new Restriction[M];
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            restrictions[i] = new Restriction(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        //part 1: assign coloring (if cant, NO) 1:left facing, 2:right facing
        adjList = new ArrayList[N+1];
        orientation = new int[N+1];
        for (int i=1;i<=N;i++){
            adjList[i] = new ArrayList<>();
        }
        for (Restriction r : restrictions){
            adjList[r.u].add(r.v);
            adjList[r.v].add(r.u);
        }
        for (int i=1;i<=N;i++){
            if (orientation[i]==0){
                orientation[i]=1;
                dfs(i);
            }
        }
        if (debug) System.out.println(Arrays.toString(orientation));
        if (debug) System.out.println(Arrays.toString(adjList));
        if (impossible) {
            out.println("NO");
            out.close();
            return;
        }

        //part 2: create directed graph. arrow signifies the child car is to its right
        toRight = new ArrayList[N+1];
        for (int i=1;i<=N;i++){
            toRight[i] = new ArrayList<>();
        }
        for (Restriction r : restrictions){
            if ((orientation[r.u]==1&&r.type==1)||(orientation[r.u]==2&&r.type==2)){
                toRight[r.u].add(r.v);
            }
            else {
                toRight[r.v].add(r.u);
            }
        }
        if (debug) System.out.println(Arrays.toString(toRight));

        //part 3: top sort the directed graph. if its not DAG, print impossible
        visited = new boolean[N+1];
        for (int i=1;i<=N;i++){
            topSort(i);
        }
        Collections.reverse(sort);
        if (debug) System.out.println(sort);

        int[] depth = new int[N+1];
        for (int i=0;i<sort.size();i++){
            depth[sort.get(i)]=i;
        }
        if (debug) System.out.println(Arrays.toString(depth));
        for (int u=1;u<=N;u++){
            for (int v : toRight[u]){
                if (depth[u]>depth[v]) {
                    out.println("NO");
                    out.close();
                    return;
                }
            }
        }
        //turn in answer
        out.println("YES");
        for (int i=1;i<=N;i++){
            if (orientation[i]==1) out.print("L ");
            else out.print("R ");
            out.print(depth[i]);
            out.println();
        }
        out.close();
    }
    public static void topSort(int node){
        if (visited[node]) return;
        visited[node] = true;
        for (int child : toRight[node]){
            topSort(child);
        }
        sort.add(node);
    }
    public static void dfs(int node){
        for (int child : adjList[node]){
            if (orientation[child]!=0){
                if (orientation[child]==orientation[node]) impossible=true;
            }
            else {
                orientation[child]=3-orientation[node];
                dfs(child);
            }
        }
    }
    private static class Restriction {
        int type;
        int u;
        int v;
        public Restriction(int t, int u, int v){
            type=t;
            this.u=u;
            this.v=v;
        }
    }
}
