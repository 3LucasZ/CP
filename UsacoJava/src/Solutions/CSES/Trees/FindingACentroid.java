package Solutions.CSES.Trees;

import java.io.*;
import java.util.*;
/*
PROB: FindingACentroid
LANG: JAVA
*/
public class FindingACentroid {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static ArrayList<Integer>[] tree;
    static int[] sz;

    public static void main(String[] args) throws IOException {
        //parse
        setup("FindingACentroid");
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }
        //find centroid
        sz = new int[N+1];
        get_subtree_size(1,0);
        out.println(get_centroid(1,0));
        out.close();
    }
    public static int get_subtree_size(int node, int par){
        for (int child : tree[node]){
            if (child==par) continue;
            sz[node]+=get_subtree_size(child,node);
        }
        sz[node]++;
        return sz[node];
    }
    public static int get_centroid(int node, int par){
        for (int child : tree[node]){
            if (child==par) continue;
            if (sz[child]*2>N) return get_centroid(child,node);
        }
        return node;
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