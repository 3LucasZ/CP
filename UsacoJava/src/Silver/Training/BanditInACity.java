package Silver.Training;

import java.io.*;
import java.util.*;
/*
Codeforces ???
D. BanditInACity
USACO Silver Training
Topic: Trees, Greedy
Thoughts:
Very nice problem... almost gave up haha
answer is max of all ceiling(citizens/leaf) and forced "citizen-leaves"
state propagation and dfs used
 */
public class BanditInACity {
    //io
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PrintWriter out = new PrintWriter(System.out);
    static boolean debug = false;
    //param
    static int N;
    static ArrayList<Integer>[] city;
    static int[] citizens;
    public static void main(String[] args) throws IOException {
        //parse input
        N = Integer.parseInt(br.readLine());
        city = new ArrayList[N+1];
        citizens = new int[N+1];
        for (int i=1;i<=N;i++) city[i] = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=2;i<=N;i++){
            int u = i;
            int v = Integer.parseInt(st.nextToken());
            city[v].add(u);
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++){
            citizens[i] = Integer.parseInt(st.nextToken());
        }
        //logic
        State fin = dfs(1);
        if (debug) System.out.println(fin.toString());
        //turn in answer
        out.println(fin.L);
        out.close();
    }
    public static State dfs(int node){
        State cur = new State(0,0,0);
        cur.citizens=citizens[node];
        if (city[node].size()==0) cur.leafs=1;

        for (int child : city[node]){
            State other = dfs(child);
            cur.L = Math.max(cur.L,other.L);
            cur.citizens+=other.citizens;
            cur.leafs+=other.leafs;
        }

        cur.L =  Math.max(cur.L, (cur.citizens+cur.leafs-1)/cur.leafs);
        return cur;
    }
    private static class State {
        long L;
        long citizens;
        int leafs;
        public State(long L, long citizens, int leafs){
            this.L=L;
            this.citizens=citizens;
            this.leafs=leafs;
        }
        public String toString(){
            return "["+L+", "+citizens+", "+leafs+"]";
        }
    }
}
