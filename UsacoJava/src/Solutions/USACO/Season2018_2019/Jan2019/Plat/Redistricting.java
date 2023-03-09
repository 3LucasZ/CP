package Solutions.USACO.Season2018_2019.Jan2019.Plat;

import java.io.*;
import java.util.*;
/*
PROB: Redistricting
LANG: JAVA
*/
public class Redistricting {
    static boolean submission = true;
    static boolean debug = false;

    static int N;
    static int K;
    static int[] p;
    static int MAX = Integer.MAX_VALUE/100;
    static int MIN = Integer.MIN_VALUE/100;

    public static void main(String[] args) throws IOException {
        //parse
        setup("redistricting");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        p = new int[N+3]; p[0]=0;
        String str = br.readLine();
        for (int i=1;i<=N;i++){
            char next = str.charAt(i-1);
            if (next=='H') p[i]=p[i-1]-1;
            else p[i]=p[i-1]+1;
        }
        if (debug) System.out.println("N: "+N+", K: "+K+", P: "+Arrays.toString(p));

        //sliding window dp init
        int[] dp = new int[N+3]; dp[0]=0;
        //dummying:
        dp[N+1] = MIN; p[N+1] = MAX; //to find j1
        p[N+2] = MAX; //to find j2
        //creating window
        TreeSet<Integer> window = new TreeSet<>((a,b)-> {
            //third id goes from lowest to highest
            if (dp[a]==dp[b]&&p[a]==p[b]) return a-b;
            //second Gcnt goes from highest to lowest
            if (dp[a]==dp[b]) return p[b]-p[a];
            //first dp goes from lowest to highest
            return dp[a]-dp[b];
        });
        window.add(0);

        //sliding window dp
        for (int i=1;i<=N;i++){
            //using j1
            Integer j1 = window.higher(N+1);
            int res1 = dp[j1]+(p[j1]<=p[i]?1:0);

            //using j2
            dp[N+2] = j1-1;
            int res2 = MAX;
            Integer j2 = window.higher(N+2);
            if (j2!=null) res2=dp[j2]+(p[j2]<=p[i]?1:0);

            //update window and dp
            dp[i] = Math.min(res1, res2);
            window.add(i);
            if (window.size()>K) window.remove(i - K);
            if (debug) System.out.println("window: "+window);
        }
        if (debug){
            while (window.size()>0){
                int next = window.pollFirst();
                out.println("i: "+next+", dp: "+dp[next]+", p: "+p[next]);
            }
        }

        //ret
        if (debug) System.out.println("DP: "+Arrays.toString(dp));
        out.println(dp[N]);
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