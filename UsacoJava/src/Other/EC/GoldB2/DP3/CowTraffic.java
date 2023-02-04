package Other.EC.GoldB2.DP3;

import java.io.*;
import java.util.*;
/*
Cow Traffic
Gold Advanced B 3
DP - extended topological sort
Notes:
Best crossover point problem
for each trail the number of paths that go through it is node1.in * node2.out
we calculate node in by calculating node in for node1, node2,...N topological style
we calculate node out similarly
elegant solution
 */
public class CowTraffic {
    //io
    static boolean submission = false;
    static boolean debug = false;
    static PrintWriter out;
    static BufferedReader br;
    //param
    static int N;
    static int M;
    static ArrayList<Integer>[] DAGf;
    static ArrayList<Integer>[] DAGb;
    static Trail[] trails;
    //dp
    static int[] pathIn;
    static int[] pathOut;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader(".in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter(".out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DAGf = new ArrayList[N+1];
        DAGb = new ArrayList[N+1];
        trails = new Trail[M];
        for (int i=1;i<=N;i++) {
            DAGf[i] = new ArrayList<>();
            DAGb[i] = new ArrayList<>();
        }
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            DAGf[u].add(v);
            DAGb[v].add(u);
            trails[i] = new Trail(u,v);
        }
        if (debug) System.out.println(Arrays.toString(DAGf));
        if (debug) System.out.println(Arrays.toString(DAGb));
        //dp forward
        pathIn = new int[N+1];
        for (int i=1;i<=N;i++) {
            for (int to : DAGf[i]){
                if (pathIn[i]==0) pathIn[i]=1;
                pathIn[to]+=pathIn[i];
            }
        }
        //dp backward
        pathOut = new int[N+1];
        for (int i=N;i>=1;i--){
            for (int from : DAGb[i]){
                if (pathOut[i]==0) pathOut[i]=1;
                pathOut[from]+=pathOut[i];
            }
        }
        if (debug) System.out.println(Arrays.toString(pathIn));
        if (debug) System.out.println(Arrays.toString(pathOut));
        //find best crossover point
        int best = 0;
        for (Trail t : trails){
            best = Math.max(pathIn[t.u]*pathOut[t.v],best);
        }
        //turn in answer
        out.println(best);
        out.close();
    }
    private static class Trail {
        int u;
        int v;
        public Trail(int u, int v){
            this.u=u;
            this.v=v;
        }
    }
}
