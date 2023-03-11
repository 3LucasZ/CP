package Helpers.Graph;

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
        int[] par;
        int[] sz;

        public DSU(int num){
            sz = new int[num+1];
            par = new int[num+1];
            Arrays.fill(sz, 1);
            Arrays.fill(par, -1);
        }

        //return parent
        public int getPar(int v){
            if (par[v] == -1) {
                return v;
            }
            par[v] = getPar(par[v]);
            return par[v];
        }

        //add edge
        public void union(int u, int v){
            int U = getPar(u);
            int V = getPar(v);
            //same component, do nothing
            if (U == V) return;
            //enforce sz[V]<sz[U]
            if (sz[U]<sz[V]){
                int tmp=U;
                U=V;
                V=tmp;
            }
            //op
            par[V] = U;
            sz[U] += sz[V];
        }
        //check CC
        public boolean connected(int u, int v){
            return getPar(u)==getPar(v);
        }
    }
}
