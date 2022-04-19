package Misc.Boilerplate.Helper;

import java.util.Arrays;

public class DSUWrapper {
    public static void main(String[] args){
        DSU dsu = new DSU(5);
        dsu.union(1,3);
        System.out.println(dsu.connected(1,3));
    }
    private static class DSU {
        int[] parent;
        int[] size;
        int[] height;

        public DSU(int num){
            height = new int[num+1];
            parent = new int[num+1];
            size = new int[num+1];
            Arrays.fill(size, 1);
            Arrays.fill(height, 1);
            Arrays.fill(parent, -1);
        };

        //return parent
        public int get(int v){
            if (parent[v] == -1) {
                return v;
            }
            parent[v] = get(parent[v]);
            return parent[v];
        }

        public int getSize(int v){
            return size[get(v)]-1;
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
                size[v_parent]+=size[u_parent];
            }
            else {
                parent[v_parent] = u_parent;
                height[u_parent] += height[v_parent];
                size[u_parent]+=size[v_parent];
            }
        }
        //check fo connected components
        public boolean connected(int u, int v){
            return get(u)==get(v);
        }
    }
}
