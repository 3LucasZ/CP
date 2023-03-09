package Solutions.USACO.Season2019_2020.Open2020.Gold;

import java.io.*;
import java.util.*;

public class FavoriteColors {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;

    static ArrayList<Integer>[] graph;
    static DSU dsu;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        //parse
        setup("fcolor");
        //setup("io/fcolor");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1]; for (int i=1;i<=N;i++) graph[i] = new ArrayList<Integer>();
        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
        }

        //sim setup
        queue = new LinkedList();
        dsu = new DSU(N);
        for (int i=1;i<=N;i++) if (graph[i].size()>1) queue.add(i);

        //sim while node exists with adjset size > 1
        while (!queue.isEmpty()) {
            int next = queue.peek();
            //next has 0 or 1 child
            if (graph[next].size() <= 1) {queue.poll();continue;}
            //get child1 and child2
            int child1 = graph[next].remove(graph[next].size()-1);
            int child2 = graph[next].get(graph[next].size()-1);
            //child1 is the same as child2, continue
            if (dsu.get(child1)==dsu.get(child2)) continue;
            //else merge
            dsu.union(child1,child2);
        }
        //make sure DSU is correct since this determines real color
        if (debug) for (int i=1;i<=N;i++){
            System.out.println(i+": "+dsu.get(i));
        }
        //get real color
        int c = 0;
        int[] color = new int[N+1];
        for (int i=1;i<=N;i++){
            if (color[dsu.get(i)]==0) color[dsu.get(i)]=++c;
            out.println(color[dsu.get(i)]);
        }
        out.close();
    }
    private static class DSU {
        int[] parent;
        int[] height;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
        }

        //return parent
        public int get(int v){
            if (parent[v] == -1) {
                return v;
            }
            parent[v] = get(parent[v]);
            return parent[v];
        }

        //add edge: modified merge union!
        public void union(int u, int v){
            int u_parent = get(u);
            int v_parent = get(v);
            if (u_parent == v_parent) return;
            //make sure u_parent is the parent(more height) and v_parent is the child(less height) during the merge
            if (height[v_parent] > height[u_parent]) {
                int tmp = u_parent;
                u_parent=v_parent;
                v_parent=tmp;
            }
            //merge nodes
            parent[v_parent] = u_parent;
            height[u_parent] += height[v_parent];
            //make sure adj1 is the larger list
            ArrayList<Integer> adj1 = graph[u_parent];
            ArrayList<Integer> adj2 = graph[v_parent];
            if (graph[v_parent].size()>graph[u_parent].size()) {
                adj1 = graph[v_parent];
                adj2 = graph[u_parent];
            }
            //merge adj lists
            for (int child : adj2) adj1.add(child);
            graph[u_parent]=adj1;
            //update the queue
            if (graph[u_parent].size()>1) queue.add(u_parent);
        }
        //check fo connected components
        public boolean connected(int u, int v){
            return get(u)==get(v);
        }
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
