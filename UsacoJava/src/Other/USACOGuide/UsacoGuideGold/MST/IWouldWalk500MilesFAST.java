package Other.USACOGuide.UsacoGuideGold.MST;

import java.io.*;
import java.util.*;

public class IWouldWalk500MilesFAST {
    static boolean submission = true;
    static boolean debug = false;

    //3 primes
    static final long x = 2019201913;
    static final long y = 2019201949;
    static final long MOD = 2019201997;
    public static void main(String[] args) throws IOException {
        setup("walk");
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(st.nextToken());

        //setup prim
        int[] cost = new int[N+1];
        boolean[] visited = new boolean[N+1];
        ArrayList<Integer> costs = new ArrayList<>();
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[1]=0;
        int next=1;

        //prim
        for (int u=1;u<=N;u++){
            if (debug) System.out.println("MST add: "+next);
            //updates
            costs.add(cost[next]);
            visited[next]=true;

            int nextNext = 0;
            for (int v=1;v<=N;v++){
                if (visited[v]) continue;
                int a = Math.min(next,v);
                int b = Math.max(next,v);
                cost[v]=Math.min(cost[v],(int)((a*x+b*y)%MOD));
                if (cost[v]<cost[nextNext]){
                    nextNext=v;
                }
            }
            next=nextNext;
        }
        if (debug){
            System.out.println(costs);
        }

        //last part: select the Kth largest edge as the answer
        Collections.sort(costs);
        out.println(costs.get((N-K+1)));
        out.close();
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
