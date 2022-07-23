package Contests.Codeforces.Round770;

import java.io.*;
import java.util.*;
/*
Round 770 (Div. 2)
E. Fair Share
Very difficult, read editorial and it made sense
too lazy to implement!!! such a pain.
make sure even outdegree for each node (or else impossible)
bipartite: arrays | elements
graph: array <--> element with multiplicity
eulerian circuit to travel through all nodes
possible since even outdegree for each node
eulerian circuit fills ans[][] array telling it L if array -> element and R if element -> array

NEW TECHNIQUE: EULER CIRCUITS
 */
public class FairShare {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = true;
    //param
    static int M;
    //graphing
    static Stack<Integer>[] arrays;
    static HashMap<Integer, Stack<Integer>> elements = new HashMap<>();
    static ArrayList<Integer>[] ans;
    public static void main(String[] args) throws IOException {
        //parse input
        M = Integer.parseInt(br.readLine());
        arrays = new Stack[M];
        ans = new ArrayList[M];
        for (int i=0;i<M;i++){
            arrays[i] = new Stack<>();
            ans[i] = new ArrayList<>();
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++){
                int u = i;
                int v = Integer.parseInt(st.nextToken());
                arrays[u].add(v);
                if (!elements.containsKey(v)) elements.put(v,new Stack<>());
                elements.get(v).add(u);
                ans[i].add(0);
            }
        }
        if (debug) {
            System.out.println(Arrays.toString(arrays));
            System.out.println(elements);
        }

        //only needed impossible check
        for (Stack<Integer> e : elements.values()){
            if (e.size()%2==1) {
                out.println("NO");
                out.close();
                return;
            }
        }

        //logic: euler circuit on bipartite graph
        for (int i=0;i<M;i++){
            while (!arrays[i].empty()) dfs1(i);
        }

        //turn in answer
        out.println();
        out.close();
    }
    //array -> element dfs
    static void dfs1(int node){
        Integer next = arrays[node].pop();
        if (next==null) return;
        ans[node].set(arrays[node].size()+1, 1);

    }
    //element -> array dfs
    static void dfs2(int node){

    }
}
