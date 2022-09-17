package Codeforces.PickNPlay;

import java.io.*;
import java.util.*;

public class MikeAndGeometryProblem {
    static boolean submission = false;
    static boolean debug = false;

    static int N;
    static int K;
    static long MOD = (long)(1e9)+7;

    //dp[i] = #points with i intersections
    static long[] dp;

    //events <event_time, delta sum>
    static TreeMap<Integer,Integer> events = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        //parse
        setup("");
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            addEvent(l,1);
            addEvent(r+1,-1);
        }
        if (debug){
            System.out.println("Events: "+events);
        }

        //build the dp
        int run = 0;
        int last_event = events.firstEntry().getKey();
        dp = new long[N+1];
        for (Map.Entry<Integer, Integer> event : events.entrySet()) {
            dp[run]+=event.getKey()-last_event;
            run+=event.getValue();
            last_event=event.getKey();
        }
        if (debug){
            System.out.println("dp[i]: "+Arrays.toString(dp));
        }

        //init choose
        Choose.init(N);

        //sum
        long ans = 0;
        for (int i=K;i<=N;i++){
            ans=(ans+Choose.get(i,K)*dp[i])%MOD;
        }

        //ret
        out.println(ans);
        out.close();
    }
    public static void addEvent(int time, int delta){
        if (!events.containsKey(time)){
            events.put(time,0);
        }
        events.put(time,events.get(time)+delta);
    }
    private static class Choose{
        //factorials
        static long[] f;
        //inverses
        static long[] i;

        static long M = (long) (1e9+7);

        public static void init(int N){
            //gen factorials (1...N)!
            f = new long[N+1];
            f[0]=1;
            for (int i=1;i<=N;i++)f[i]=(f[i-1]*i)%M;

            //gen inverses (1...N)!^-1
            i = new long[N+1];
            for (int A=1;A<=N-1;A++){
                i[A]=pow(f[A],M-2);
            }

            //debug
            if (debug) {
                System.out.println(Arrays.toString(f));
                System.out.println(Arrays.toString(i));
            }
        }
        public static long get(int n, int k){
            if (k==n || k==0) return 1;
            return ((f[n]*i[k]%M)*i[n-k])%M;
        }
        public static long pow(long x, long p){
            if(p==0) return 1;
            if(p%2==1)return (x*pow(x,p-1))%M;
            else return pow((x*x)%MOD,p/2);
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
