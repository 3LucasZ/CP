package Helpers.Graph;

import java.util.ArrayList;

public class LCABinaryLifting {
    /*
    Goal: LCA (u,v)
    Conditions:  1-indexed tree
    Time Complexity:
     */
    private static class LCA {
        int size;
        int root;
        ArrayList<Integer>[] tree;

        //[node][2^nth parent]
        int[][] ancestor;
        int[] height;
        int maxHeight = 0;

        int lg = (int) (Math.log(maxHeight)/Math.log(2));

        public LCA(ArrayList<Integer>[] tree, int root){
            this.size=tree.length-1;
            this.root=root;
            this.tree=tree;

            height = new int[size+1];
            DFS_height(root, 0, 0);

            ancestor = new int[size+1][maxHeight+1];
            DFS_ancestor(root, 0);
        }
        public int getAncestor(int node, int a){
            if (a > height[node]) return -1;
            int ret = node;

            int pow = 0;
            while (a>0){
                if (a%2==1) ret=ancestor[ret][pow];
                a = a >> 1;
                pow++;
            }
            return ret;
        }
        public int getLCA(int u, int v){
            //p1 move to same level
            if (height[v]>height[u]){
                int tmp = v;
                v=u;
                u=tmp;
            }
            u = getAncestor(u, height[u]-height[v]);
            if (u==v) return u;

            //p2 binary lifting

            for (int log=lg;log>=0;log--){
                if (ancestor[u][log]!=ancestor[v][log]){
                    u=ancestor[u][log];
                    v=ancestor[v][log];
                }
            }
            return ancestor[u][0];
        }
        public void DFS_height(int node, int par, int h){
            height[node]=h;
            maxHeight=Math.max(maxHeight,h);
            for (int child : tree[node]){
                if (child!=par) DFS_height(child,node,h+1);
            }
        }
        public void DFS_ancestor(int node, int par){
            if (node!=root) ancestor[node][0]=par;
            int a = 1;
            while ((int)(Math.pow(2,a))<=height[node]){
                ancestor[node][a]=ancestor[ancestor[node][a-1]][a-1];
                a++;
            }
            for (int child : tree[node]){
                if (child!=par)DFS_ancestor(child,node);
            }
        }
    }
}
