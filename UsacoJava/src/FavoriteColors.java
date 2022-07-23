import java.io.*;
import java.util.*;

public class FavoriteColors {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int M;

    static HashSet<Integer>[] adjset;
    static DSU dsu;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        //parse
        setup("fcolor");
        //setup("UsacoJava/io/fcolor");

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        adjset = new HashSet[N+1]; for (int i=1;i<=N;i++) adjset[i] = new HashSet<Integer>();

        for (int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adjset[u].add(v);
        }

        //log
        //setup
        queue = new LinkedList();
        dsu = new DSU(N);
        for (int i=1;i<=N;i++) if (adjset[i].size()>1) queue.add(i);

        //while node exists with adjset size > 1
        while (!queue.isEmpty()) {
            int next = dsu.get(queue.poll()); if (adjset[next].size() <= 1) continue;

            //get id: the head after all unions
            int head = -1;
            for (int node : adjset[next]){
                node = dsu.get(node);
                if (head!=-1) {
                    dsu.union(node, head);
                }
                head=node;
            }

            int initSize = adjset[head].size();

            //create complete merged set
            HashSet<Integer> set = new HashSet<>();
            for (int node : adjset[next]) {
                node = dsu.get(node);
                set.addAll(adjset[node]);
            }
            adjset[head] = set;

            if (set.size() > initSize) queue.add(head);
        }

        if (debug) for (int i=1;i<=N;i++){
            System.out.println(i+": "+dsu.get(i));
        }

        //get real color
        int co = 0;
        int[] color = new int[N+1];
        for (int i=1;i<=N;i++){
            if (color[dsu.get(i)]==0) color[dsu.get(i)]=++co;
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

        //add edge
        public void union(int u, int v){
            int u_parent = get(u);
            int v_parent = get(v);
            //same component, do nothing
            if (u_parent == v_parent) return;
            if (height[u_parent] < height[v_parent]){
                parent[u_parent] = v_parent;
                height[v_parent] += height[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                height[u_parent] += height[v_parent];
            }
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
