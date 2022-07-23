import java.io.*;
import java.util.*;

public class NewRoadsQueries {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;

    //param
    static int N;
    static int M;
    static int Q;

    static ArrayList<Edge>[] forest;

    public static void main(String[] args) throws IOException {
        //parse
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        //init
        DSU dsu = new DSU(N);
        forest = new ArrayList[N+1]; for (int i=1;i<=N;i++) forest[i] = new ArrayList<>();

        //create weighted forest with dsu
        for (int i=1;i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (dsu.connected(u,v)) continue;
            else {
                dsu.union(u,v);
                forest[u].add(new Edge(u,v,i));
                forest[v].add(new Edge(v,u,i));
            }
        }
        if (debug){
            out.println("Forest: "+Arrays.toString(forest));
        }

        //handle queries
        LCA lca = new LCA(forest);
        if (debug){
            out.println(Arrays.toString(lca.getAncestor(5,4)));
            out.println(lca.getLCA(5,6));
        }
        for (int i=0;i<Q;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (!dsu.connected(u,v)) out.println(-1);
            else {
                out.println(lca.getLCA(u,v));
            }
        }
        out.close();
    }
    private static class LCA {
        //precondition: 1-indexed parForest

        int size;
        ArrayList<Edge>[] forest;
        boolean[] visited;

        //[i][j] = node i's 2^j parent
        int[][] ancestor;

        //[i][j] = node i's max edge up to 2^j parent
        int[][] mx;

        //[i] = height of node i in the forest
        int[] height;

        int lg = 0;

        public LCA(ArrayList<Edge>[] forest){
            this.size=forest.length-1;
            this.forest=forest;

            int k=1;
            while (k<size){
                k*=2;
                lg++;
            }

            height = new int[size+1];
            ancestor = new int[size+1][lg+1];
            mx = new int[size+1][lg+1];
            visited = new boolean[size+1];

            for (int i=1;i<=size;i++) {
                if (visited[i]) continue;
                DFS_height(i, 0, 0);
                DFS_ancestor(i, 0,0);
            }
        }
        //returns (ancestor a, largest cost on path to a)
        public int[] getAncestor(int node, int a){
            int retNode = node;
            int retCost = 0;

            int pow = 0;
            while (a>0){
                if (a%2==1) {
                    retCost=Math.max(retCost,mx[retNode][pow]);
                    retNode=ancestor[retNode][pow];
                }
                a = a >> 1;
                pow++;
            }
            return new int[]{retNode, retCost};
        }
        public int getLCA(int u, int v){
            //p1 move to same level
            int cost = 0;
            //make sure u has greater height
            if (height[v]>height[u]){
                int tmp = v;
                v=u;
                u=tmp;
            }
            int[] res = getAncestor(u, height[u]-height[v]);
            u = res[0];
            cost=res[1];

            //u was a direct child of v
            if (u==v) return cost;
            if (debug) out.println("Part1 cost: "+cost);

            //p2 binary lifting
            for (int log=lg;log>=0;log--){
                if (ancestor[u][log]!=ancestor[v][log]){
                    cost=Math.max(cost,Math.max(mx[u][log],mx[v][log]));
                    u=ancestor[u][log];
                    v=ancestor[v][log];
                }
            }
            return Math.max(cost,Math.max(mx[u][0],mx[v][0]));
        }
        public void DFS_height(int node, int par, int h){
            visited[node]=true;
            height[node]=h;
            for (Edge childEdge : forest[node]){
                int child = childEdge.v;
                if (child!=par) DFS_height(child,node,h+1);
            }
        }
        public void DFS_ancestor(int node, int par, int cost){
            if (par!=0){
                ancestor[node][0]=par;
                mx[node][0]=cost;

                int a = 1;
                while ((int)(Math.pow(2,a))<=height[node]){
                    ancestor[node][a]=ancestor[ancestor[node][a-1]][a-1];
                    mx[node][a]=Math.max(mx[node][a-1],mx[ancestor[node][a-1]][a-1]);
                    a++;
                }
            }

            for (Edge childEdge : forest[node]){
                int child = childEdge.v;
                if (child!=par)DFS_ancestor(child,node,childEdge.c);
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int c;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.c=c;
        }
        public String toString(){
            return "["+u+", "+v+": "+c+"]";
        }
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
}

/*
6 5 0
1 2
2 3
3 4
4 5
1 6
 */