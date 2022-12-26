package Helper.Graph;

import java.util.Arrays;

public class DSUWrapper {
    public static void main(String[] args){
        DSU dsu = new DSU(5);
        dsu.union(1,3);
        dsu.union(2,4);
        dsu.union(1,4);
        System.out.println(dsu.connected(1,4));
    }
    private static class DSU {
        /*
        union and get operations
        sz is the size of the component
         */
        int[] parent;
        int[] sz;

        public DSU(int num){
            sz = new int[num+1];
            parent = new int[num+1];
            Arrays.fill(sz, 1);
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
            int U = get(u);
            int V = get(v);
            //same component, do nothing
            if (U == V) return;
            //enforce sz[V]<sz[U]
            if (sz[U]<sz[V]){
                int tmp=U;
                U=V;
                V=tmp;
            }
            //op
            parent[V] = U;
            sz[U] += sz[V];
        }
        //check CC
        public boolean connected(int u, int v){
            return get(u)==get(v);
        }
    }
}
