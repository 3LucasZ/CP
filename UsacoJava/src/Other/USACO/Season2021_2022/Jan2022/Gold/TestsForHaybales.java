package Other.USACO.Season2021_2022.Jan2022.Gold;

import java.io.*;
import java.util.*;
/*
PROB: TestsForHaybales
LANG: JAVA
*/
public class TestsForHaybales {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int[] ji;

    static ArrayList<Integer>[] tree;
    static int K;
    static int value;
    static int[] xi;
    static int[] hi;

    public static void main(String[] args) throws IOException {
        //*parse
        setup("TestsForHaybales");
        N = Integer.parseInt(br.readLine());
        ji = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            ji[i]=Integer.parseInt(st.nextToken());
        }
        //*create the tree
        tree = new ArrayList[N+2]; for (int i=1;i<=N+1;i++) tree[i] = new ArrayList<>();
        for (int u=1;u<=N;u++){
            int v = ji[u]+1;
            tree[v].add(u);
        }
        //sort the tree children from largest to smallest
        for (int i=1;i<=N+1;i++) Collections.reverse(tree[i]);
        if (debug) System.out.println("tree: "+Arrays.toString(tree));
        //*DFS to find: bale[hi,xi]
        K = N+1;
        xi = new int[N+2]; hi = new int[N+2];
        value=K-1;
        DFS(N+1,0);
        //fix hi
        int fix = 0;
        for (int i=1;i<=N;i++) fix=Math.min(fix,hi[i]);
        for (int i=1;i<=N;i++) hi[i]-=fix;
        if (debug) System.out.println("hi: "+Arrays.toString(hi));
        if (debug) System.out.println("xi: "+Arrays.toString(xi));
        //*ret
        out.println(K);
        for (int i=1;i<=N;i++) out.println((long)hi[i]*K+xi[i]);
        out.close();
    }

    public static void DFS(int node, int par){
        xi[node]=value;
        hi[node]=hi[par]-1;
        value--;
        for (int child : tree[node]){
            DFS(child,node);
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