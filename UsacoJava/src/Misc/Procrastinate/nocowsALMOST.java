package Misc.Procrastinate;

import java.io.*;
import java.util.*;

/*
LANG: JAVA
TASK: nocows
 */
public class nocowsALMOST {
    //io
    static boolean submission = true;
    static PrintWriter out;
    static BufferedReader br;

    //param
    static int N;
    static int K;

    //BFS, DP, FF
    static Queue<State> BFS = new LinkedList<>();
    static int[][][] freq;
    static boolean[][][] visited;

    //choose DP
    static long[][] choose;

    //helper
    static int MOD = 9901;

    public static void main(String[] args) throws IOException {
        //io
        if (submission) {
            br = new BufferedReader(new FileReader("nocows.in"));
            out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
        }
        else {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
        }
        //parse input
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        //create choose table
        choose = new long[N+1][N+1];
        for (int n=0;n<=N;n++){
            choose[n][0]=1;
            for (int k=1;k<n;k++){
                choose[n][k]=((choose[n][k-1]*(n-k+1))/k);
            }
            choose[n][n]=1;
        }
        if (!submission){
            for (int n=0;n<=N;n++){
                System.out.println(Arrays.toString(choose[n]));
            }
            System.out.println();
        }

        //INIT
        freq = new int[N+1][K+1][N+1];
        visited = new boolean[N+1][K+1][N+1];
        BFS.add(new State(1,1,1));
        freq[1][1][1]=1;
        visited[1][1][1]=true;

        //BFS, DP, FF
        while (!BFS.isEmpty()) {
            State next = BFS.poll();
            if (next.height==K) continue;
            for (int nodes=2;nodes<=Math.min(N-next.nodes,2*next.bottom);nodes+=2) {
                freq[next.nodes+nodes][next.height+1][nodes] =
                        (freq[next.nodes+nodes][next.height+1][nodes] +
                                freq[next.nodes][next.height][next.bottom]*(int)(choose[next.bottom][nodes/2]%MOD))%MOD;
                if (visited[next.nodes+nodes][next.height+1][nodes]) continue;
                visited[next.nodes+nodes][next.height+1][nodes]=true;
                BFS.add(new State(next.nodes+nodes, next.height+1, nodes));
            }
        }
        if (!submission) {
            System.out.println(freq[1][1][1]);
            System.out.println(freq[3][2][2]);
            System.out.println(freq[5][3][2]);
        }
//        int sum2=0;
//        for (int i=0;i<=K;i++) for (int j=0;j<=N;j++) sum2=(sum2+freq[j][K][i])%MOD;
//        out.println(sum2);
//        out.close();
        int sum2=0;
        for (int i=0;i<=K;i++){
            sum2 = (sum2+freq[N][K][i])%MOD;
        }
        out.println(sum2);
        out.close();
        //STEP 2: coin knapsack on {good height}
        ArrayList<Pair> goodHeight = new ArrayList<>();
        for (int nodes=0;nodes<=N;nodes++){
            int sum = 0;
            for (int bottom=0;bottom<=N;bottom++){
                sum = (sum + freq[nodes][K][bottom])%MOD;
            }
            if (sum!=0) goodHeight.add(new Pair(nodes, sum));
        }
        if (!submission){
            System.out.println(goodHeight);
        }



        //DP again :)
        //ans[nodes][last height]
        int[][] ans = new int[N+1][goodHeight.size()];
        //for (int last=0;last<goodHeight.size();last++)ans[0][last]=1;
        if (goodHeight.size()>0)ans[0][0]=1;
        for (int nodes=0;nodes<=N;nodes++){
            for (int last=0;last<goodHeight.size();last++){
                for (int nextLast=last;nextLast< goodHeight.size();nextLast++){
                    int search = nodes+goodHeight.get(nextLast).nodes;
                    if (search > N) continue;
                    ans[search][nextLast]=(ans[search][nextLast]+ans[nodes][last]*goodHeight.get(nextLast).freq)%MOD;
                }
            }
        }
        if (!submission){
            for (int nodes=0;nodes<=N;nodes++) System.out.println(Arrays.toString(ans[nodes]));
        }
        //turn in answer
        int ret = 0;
        for (int i=0;i<goodHeight.size();i++) ret=(ret+ans[N][i])%MOD;
        out.println(ret);
        out.close();
    }
    private static class Pair {
        int nodes;
        int freq;
        public Pair(int n, int f){
            nodes=n;
            freq=f;
        }
        public String toString(){
            return "["+nodes+": "+freq+"]";
        }
    }
    private static class State {
        int nodes;
        int height;
        int bottom;
        public State(int n, int h, int b){
            nodes=n;
            height=h;
            bottom=b;
        }
    }
}
