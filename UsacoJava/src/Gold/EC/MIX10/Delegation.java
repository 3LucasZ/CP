package Gold.EC.MIX10;

import java.io.*;
import java.util.*;
/*
USACO 2020 February Contest, Gold
Problem 3. Delegation
USACO Gold Training
- old EC problem mix11 FINALLY solved, thanks to epic usaco editorial
beautiful tree problem, gosh, tree dp solve roots first
tree partition into paths
path pairing using global array, efficient array cleanup
arbitrary root - tree dangle

weird debug:
30 min debug :) haha to find out that unpair was calculated incorrectly - causing 2 tcs wrong - dont give up!
 */
public class Delegation {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static ArrayList<Integer>[] tree;

    static int[] stemLengths;

    public static void main(String[] args) throws IOException {
        setup("deleg");
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1]; for (int i=1;i<=N;i++) tree[i] = new ArrayList<>();
        for (int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        stemLengths = new int[N+1];

        HashSet<Integer> bad = new HashSet<>();
        for (int K=1;K<=N-1;K++){
            if (!bad.contains(K)&&(N-1)%K==0 && tryPartition(K)) out.print(1);
            else {
                for (int j=1;K*j<=N;j++){
                    bad.add(K*j);
                }
                out.print(0);
            }
        }
        out.close();
    }
    public static boolean tryPartition(int len){
        return DFS(1,0, len)==0;
    }

    public static int DFS(int node, int par, int len){
        ArrayList<Integer> stems = new ArrayList<>();
        for (int child : tree[node]){
            if (child!=par) {
                //get stem
                int stem = DFS(child,node,len);
                //propagate bad stem
                if (stem==-1) return -1;
                //process
                stem=(stem+1)%len;
                if (stem!=0) stems.add(stem);
            }
        }

        int unPaired = 0;
        //all children finished processing, final process
        for (int stem : stems) {
            //if root stem conjugate is available
            if (stemLengths[len - stem] > 0) {
                unPaired--;
                stemLengths[len - stem]--;
            }
            //root stem conjugate unavailable
            else {
                unPaired++;
                stemLengths[stem]++;
            }
        }

        //clean up
        int ret = 0;
        for (int stem : stems){
            if (stemLengths[stem]!=0) ret = stem;
            stemLengths[stem]=0;
        }

        //return based on stems
        if (unPaired <= 1) return ret;
        return -1;
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
/*
diff:
me: 11011000010000000001
comp: 1100100001

problemo?
me: 11101
comp: 11001
 */