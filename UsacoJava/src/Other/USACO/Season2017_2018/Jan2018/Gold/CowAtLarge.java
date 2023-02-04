package Other.USACO.Season2017_2018.Jan2018.Gold;

import java.io.*;
import java.util.*;
/*
USACO 2018 January Contest, Gold
Problem 2. Cow at Large
USACO Gold Training
Thoughts: Tree Algorithms
My solution: (very different from official but makes sense to me lol)
process leafs by shallowness
after process leaf, close all leafs that are caught by shallow leaf
greedy because we know
shallow can save deep sometimes
if deep saves shallow, shallow can save deep
^^ loop thru until all leaf is visited
 */
public class CowAtLarge {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int K;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static ArrayList<Integer> leafSort = new ArrayList<>();
    static int[] depth;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("atlarge.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("atlarge.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        //logic
        depth = new int[N+1];
        DFS(K, 0, 1);
        Collections.sort(leafSort, (a,b)->depth[a]-depth[b]);
        if (!submission) {
            System.out.println(Arrays.toString(depth));
            System.out.println(leafSort);
        }

        visited = new boolean[N+1];

        int cnt = 0;
        for (int leaf : leafSort){
            if (visited[leaf]) continue;
            cnt++;
            int mid = depth[leaf]/2+1;
            int head = leaf;
            while (depth[head]!=mid) for (int child : tree[head]) if (depth[child]==depth[head]-1) head = child;
            if (!submission) System.out.println(head);
            FF(head);
        }

        //turn in answer
        out.println(cnt);
        out.close();
    }
    public static void FF(int node){
        visited[node]=true;
        for (int child : tree[node]){
            if (depth[child]==depth[node]+1) FF(child);
        }
    }
    public static void DFS(int node, int par, int height){
        depth[node]=height;

        //process tunnel
        if (tree[node].size()==1) leafSort.add(node);
        for (int child : tree[node]){
            if (child==par) continue;
            DFS(child, node, height+1);
        }
    }
}
