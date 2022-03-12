import java.util.*;
import java.io.*;
/*
Getting Kth ancestor of node U in log(K) time
inspiration: AlyonaAndATree

 */
public class TreeAncestorBinaryJumping {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static boolean debug = true;

    //in
    static int N;
    static ArrayList<Integer>[] tree;
    static int[] parent;
    //[node][2^r ancestor]
    static int[][] ancestor;

    public static void main(String[] args) throws IOException {
        //init
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        parent = new int[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            parent[v]=u;
        }
        if (debug){
            System.out.println(Arrays.toString(tree));
            System.out.println(Arrays.toString(parent));
        }

        //preprocess
        preprocess();

        //queries
        System.out.println(getAncestor(10, 5));
        System.out.println(getAncestor(10, 4));
        System.out.println(getAncestor(8, 3));
        System.out.println(getAncestor(4, 2));
    }

    public static int getAncestor(int node, int up){
        int ret = node;
        int cnt=1;
        while (up > 0){
            if (up % 2 == 1) ret = ancestor[ret][cnt];
            cnt++;
            up = up >> 1;
        }
        return ret;
    }
    public static void preprocess(){
        ancestor = new int[N+1][bits(N)+1];
        dfs(1,0);
        for (int i=2;i<=N;i++) System.out.println(Arrays.toString(ancestor[i]));
    }

    public static void dfs(int node, int height){
        int calcs = bits(height);
        for (int i=1;i<=calcs;i++){
            if (i==1) ancestor[node][i] = parent[node];
            else ancestor[node][i] = ancestor[ancestor[node][i-1]][i-1];
        }
        for (int child : tree[node]){
            dfs(child, height+1);
        }
    }

    public static int bits(int num){
        int cnt = 0;
        while (num > 0) {
            num = num >> 1;
            cnt++;
        }
        return cnt;
    }
}

/*
Data:
10
1 3
3 4
1 2
2 5
2 6
3 7
8 9
5 8
9 10
 */
