package USACO.Season2021_2022.Dec2021.Gold;

import java.io.*;
import java.util.*;

public class PairedUp {
    static boolean submission = false;
    static boolean debug = false;

    static int T;
    static int N;
    static int K;

    static TreeMap<Integer, Integer> xi = new TreeMap<>();
    static ArrayList<Pair> span = new ArrayList<>();

    static int ans;

    static int INF = (int) (1e9+1);

    public static void main(String[] args) throws IOException {
        //parse
        setup("io/pairedup");
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int prevX = -1;
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y  = Integer.parseInt(st.nextToken());
            if (x-prevX>K&&prevX!=-1) {
                ans+=(T==1?minUnpaired(span):maxUnpaired(span));
                span.clear();
            }
            span.add(new Pair(x,y));
            prevX=x;
        }
        if (span.size()>0) ans+=(T==1?minUnpaired(span):maxUnpaired(span));

        //ret
        out.println(ans);
        out.close();
    }
    public static int minUnpaired(ArrayList<Pair> span){
        if (span.size()%2==0) return 0;
        int ret = INF;
        for (int i=0;i<span.size();i++){
            if (i%2==0||span.get(i+1).x-span.get(i-1).x<=K) ret=Math.min(ret,span.get(i).y);
        }
        return ret;
    }
    public static int maxUnpaired(ArrayList<Pair> span){
        //trivial cases
        if (span.size()==1) return span.get(0).y;
        if (span.size()==2) return 0;
        //init
        xi.clear();
        int S = span.size();
        int[][] dp = new int[S][2]; //dp[ith cow][0=even unpaired/1=odd unpaired]=max sum unpaired
        //base case
        dp[0][0]=0;
        dp[0][1]=span.get(0).y;
        xi.put(span.get(0).x,0);

        //transitions
        for (int i=1;i<S;i++){
            dp[i][0]=dp[i-1][0];
            dp[i][1]=dp[i-1][1];
            Integer lower = xi.lowerKey(span.get(i).x-K);
            int lb = lower==null?-1:xi.get(lower);
            if (i==S-1 || span.get(i+1).x-span.get(i-1).x <= K || (i+1)%2==0) {
                dp[i][0]=Math.max(dp[i][0],(lb==-1?-INF:dp[lb][1])+span.get(i).y);
            }
            if (i==S-1 || span.get(i+1).x-span.get(i-1).x <= K || (i+1)%2==1){
                dp[i][1]=Math.max(dp[i][1],(lb==-1?0:dp[lb][0])+span.get(i).y);
            }
            xi.put(span.get(i).x,i);
        }

        //ret
        if (debug){
            System.out.println("Span: "+span);
            System.out.println("Score: "+dp[S-1][S%2]);
            System.out.println("DP: ");
            for (int i=0;i<S;i++){
                for (int j=0;j<2;j++){
                    System.out.print(dp[i][j]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return dp[S-1][S%2];
    }
    private static class Pair {
        int x;
        int y;
        public Pair(int x, int y){
            this.x=x;
            this.y=y;
        }
        public String toString() {
            return "[" + x + ", " + y + "]";
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
