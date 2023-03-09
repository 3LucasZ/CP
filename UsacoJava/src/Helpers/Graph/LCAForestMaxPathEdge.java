package Helpers.Graph;
import java.util.*;
public class LCAForestMaxPathEdge {
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

        public LCA(ArrayList<Edge>[] forest) {
            this.size = forest.length - 1;
            this.forest = forest;

            int k = 1;
            while (k < size) {
                k *= 2;
                lg++;
            }

            height = new int[size + 1];
            ancestor = new int[size + 1][lg + 1];
            mx = new int[size + 1][lg + 1];
            visited = new boolean[size + 1];

            for (int i = 1; i <= size; i++) {
                if (visited[i]) continue;
                DFS_height(i, 0, 0);
                DFS_ancestor(i, 0, 0);
            }
        }

        //returns (ancestor a, largest cost on path to a)
        public int[] getAncestor(int node, int a) {
            int retNode = node;
            int retCost = 0;

            int pow = 0;
            while (a > 0) {
                if (a % 2 == 1) {
                    retCost = Math.max(retCost, mx[retNode][pow]);
                    retNode = ancestor[retNode][pow];
                }
                a = a >> 1;
                pow++;
            }
            return new int[]{retNode, retCost};
        }

        public int getLCA(int u, int v) {
            //p1 move to same level
            int cost = 0;
            //make sure u has greater height
            if (height[v] > height[u]) {
                int tmp = v;
                v = u;
                u = tmp;
            }
            int[] res = getAncestor(u, height[u] - height[v]);
            u = res[0];
            cost = res[1];

            //u was a direct child of v
            if (u == v) return cost;

            //p2 binary lifting
            for (int log = lg; log >= 0; log--) {
                if (ancestor[u][log] != ancestor[v][log]) {
                    cost = Math.max(cost, Math.max(mx[u][log], mx[v][log]));
                    u = ancestor[u][log];
                    v = ancestor[v][log];
                }
            }
            return Math.max(cost, Math.max(mx[u][0], mx[v][0]));
        }

        public void DFS_height(int node, int par, int h) {
            visited[node] = true;
            height[node] = h;
            for (Edge childEdge : forest[node]) {
                int child = childEdge.v;
                if (child != par) DFS_height(child, node, h + 1);
            }
        }

        public void DFS_ancestor(int node, int par, int cost) {
            if (par != 0) {
                ancestor[node][0] = par;
                mx[node][0] = cost;

                int a = 1;
                while ((int) (Math.pow(2, a)) <= height[node]) {
                    ancestor[node][a] = ancestor[ancestor[node][a - 1]][a - 1];
                    mx[node][a] = Math.max(mx[node][a - 1], mx[ancestor[node][a - 1]][a - 1]);
                    a++;
                }
            }

            for (Edge childEdge : forest[node]) {
                int child = childEdge.v;
                if (child != par) DFS_ancestor(child, node, childEdge.c);
            }
        }

    }

    private static class Edge {
        int u;
        int v;
        int c;

        public Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }

        public String toString() {
            return "[" + u + ", " + v + ": " + c + "]";
        }
    }
}
