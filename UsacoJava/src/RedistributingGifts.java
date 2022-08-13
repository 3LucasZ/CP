import java.io.*;
import java.util.*;

public class RedistributingGifts {
    static boolean submission = false;
    static boolean debug = true;

    static int N;
    static boolean[][] graph;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        N = Integer.parseInt(br.readLine());
        graph = new boolean[N][N];
        parent = new int[N];
        for (int cow=0;cow<N;cow++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int favor=0;favor<N;favor++){
                int gift = Integer.parseInt(st.nextToken())-1;
                graph[cow][gift]=true;
                if (gift==cow) break;
            }
        }
        if (debug){
            for (int i=0;i<N;i++) System.out.println(Arrays.toString(graph[i]));
        }

        //dp init
        int sets = 1<<N;
        dp = new long[sets];
        dp[0]=1;
        //bitset dp
        for (int set=1;set<sets;set++){
            if (debug) System.out.println("Set: "+set);
            int add = 0;
            boolean[] inSet = new boolean[N];
            for (int bit=0;bit<N;bit++) if (((1<<bit)&set)!=0) {
                inSet[bit]=true;
                add=bit;
            }
            if (debug) System.out.println("Last node: "+add);

            Arrays.fill(parent,-1);
            DFS(add, set, inSet, add);
            if (debug) System.out.println("DP: "+dp[set]);
        }

        //handle queries
        int Q = Integer.parseInt(br.readLine());
        for (int q=0;q<Q;q++){
            String str = br.readLine();
            int H = 0;
            int G = 0;
            for (int i=0;i<N;i++){
                if (str.charAt(i)=='H') H+=(1<<i);
                else G+=(1<<i);
            }
            if (debug){
                out.println("H: "+H+", G:"+G);
            }
            out.println(dp[H]*dp[G]);
        }
        out.close();
    }
    static int[] parent;
    public static void DFS(int node, int set, boolean[] inSet, int root){
        for (int child=0;child<N;child++){
            //valid child
            if (inSet[child]&&graph[node][child]){
                //back edge and not to root
                if (parent[child]!=-1) {}
                //cycle containing root
                else if (child == root) {
                    int cycle = 0;
                    int cur = node;
                    while (cur != -1) {
                        cycle += (1<<cur);
                        cur = parent[cur];
                    }
                    if (debug) System.out.println("Cycle: "+cycle);
                    dp[set] += dp[set^cycle];
                }
                //regular child edge
                else {
                    parent[child]=node;
                    DFS(child,set,inSet,root);
                }
            }
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
