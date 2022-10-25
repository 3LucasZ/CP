package Helper.Graph;

import java.util.ArrayList;

public class AncestorDescendant {
    private static class AncDesc {
        //1 indexed tree
        ArrayList<Integer>[] tree;
        int[] in;
        int[] out;
        int timer = 0;
        public AncDesc(int N, ArrayList<Integer>[] tree){
            this.tree=tree;
            in = new int[N+1];
            out = new int[N+1];
            DFS(1,0);
        }
        public boolean u_in_v(int u, int v){
            return in[u]>=in[v] && out[u]<=out[v];
        }
        public void DFS(int node, int par){
            in[node]=timer++;
            for (int child : tree[node]){
                if (child==par) continue;
                DFS(child,node);
            }
            out[node]=timer++;
        }
    }
}
