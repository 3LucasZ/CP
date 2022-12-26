package Helper.Tree;

import java.util.*;
public class CompressTreeWrapper {
    private static class CompressTree {
        //roots the tree at 1 and makes it directional (irreversible)
        //edges with degree 1 are compressed
        ArrayList<Edge>[] tree;
        public CompressTree(ArrayList<Edge>[] tree){
            this.tree=tree;
            directional(1,0);
            compress(1);
        }
        void directional(int node, int par){
            ListIterator<Edge> it = tree[node].listIterator();
            while (it.hasNext()){
                Edge next = it.next();
                if (next.v==par) it.remove();
                else directional(next.v,node);
            }
        }
        void compress(int node){
            ListIterator<Edge> it = tree[node].listIterator();
            while (it.hasNext()){
                Edge next = it.next();
                if (tree[next.v].size()==1){
                    it.remove();
                    Edge nextNext = tree[next.v].get(0);
                    it.add(new Edge(node,nextNext.v,nextNext.cost+next.cost));
                    tree[next.v] = new ArrayList<>();
                    it.previous();
                }
                else {
                    compress(next.v);
                }
            }
        }
    }
    private static class Edge {
        int u;
        int v;
        int cost;
        public Edge(int u, int v, int c){
            this.u=u;
            this.v=v;
            this.cost=c;
        }
        public String toString(){
            return "["+u+", "+v+", "+cost+"]";
        }
    }

}
